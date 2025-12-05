package com.mycompany.proyecto_u1.services;

// Importaciones de Modelos y Java
import com.mycompany.proyecto_u1.models.Encuesta;
import com.mycompany.proyecto_u1.models.Pregunta;
import com.mycompany.proyecto_u1.models.Respuesta;
import com.mycompany.proyecto_u1.models.RespuestaEncuesta;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Importaciones de EXCEL (Apache POI)
import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// Importaciones de PDF (PDFBox)
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;


public class ReporteService {


    private static String getTextoLikert(int puntuacion) {
        switch (puntuacion) {
            case 1: return "1 - Totalmente en Desacuerdo";
            case 2: return "2 - En Desacuerdo";
            case 3: return "3 - Neutral";
            case 4: return "4 - De Acuerdo";
            case 5: return "5 - Totalmente de Acuerdo";
            default: return String.valueOf(puntuacion); // Si es 0 o algo raro
        }
    }

    


   public static boolean generarExcelReporteGeneral(File file) {
        // --- CORRECCIÓN: Instanciar los servicios primero ---
        EncuestaService encuestaService = new EncuestaService();
        RespuestaService respuestaService = new RespuestaService();

        // 1. Obtener todos los datos necesarios usando las instancias
        ArrayList<Encuesta> todasLasEncuestas = encuestaService.getEncuestas();
        ArrayList<RespuestaEncuesta> todasLasRespuestas = respuestaService.getRespuestas(); 
        
        // 2. Filtrar solo las encuestas publicadas
        List<Encuesta> encuestasPublicadas = todasLasEncuestas.stream()
                .filter(Encuesta::isEstaPublicada)
                .collect(Collectors.toList());

        // 3. Crear el libro de Excel
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            
            // --- PESTAÑA 1: RESUMEN DE PROMEDIOS ---
            XSSFSheet sheetPromedios = workbook.createSheet("Resumen de Promedios");
            int rowNumPromedios = 0;
            
            // Encabezados
            Row headerPromedios = sheetPromedios.createRow(rowNumPromedios++);
            headerPromedios.createCell(0).setCellValue("Encuesta");
            headerPromedios.createCell(1).setCellValue("Pregunta");
            headerPromedios.createCell(2).setCellValue("Promedio (1-5)");
            headerPromedios.createCell(3).setCellValue("Total de Respuestas");

            // Calcular y llenar los promedios
            for (Encuesta encuesta : encuestasPublicadas) {
                
                Map<String, int[]> resultados = new java.util.HashMap<>();
                
                // Inicializar mapa con preguntas
                for (Pregunta p : encuesta.getPreguntas()) {
                    if (p.esCompleja()) { 
                        for (Pregunta subP : p.getSubPreguntas()) { 
                            resultados.put(subP.getTexto(), new int[5]); 
                        }
                    } else {
                        resultados.put(p.getTexto(), new int[5]); // Agregar pregunta simple
                    }
                }
                
                // Filtrar respuestas
                for (RespuestaEncuesta re : todasLasRespuestas) {
                    if (re.getTituloEncuesta().equals(encuesta.getTitulo())) {
                        for (Respuesta r : re.getRespuestas()) {
                            if (resultados.containsKey(r.getTextoPregunta())) {
                                int puntuacion = r.getPuntuacion(); 
                                if (puntuacion >= 1 && puntuacion <= 5) {
                                    resultados.get(r.getTextoPregunta())[puntuacion - 1]++;
                                }
                            }
                        }
                    }
                }
                
                // Escribir en Excel
                if (resultados.isEmpty()) {
                    Row row = sheetPromedios.createRow(rowNumPromedios++);
                    row.createCell(0).setCellValue(encuesta.getTitulo());
                    row.createCell(1).setCellValue("- Sin Preguntas -");
                } else {
                    for (Map.Entry<String, int[]> entry : resultados.entrySet()) {
                        String pregunta = entry.getKey();
                        int[] conteos = entry.getValue();
                        
                        double sumaTotal = (conteos[0]*1) + (conteos[1]*2) + (conteos[2]*3) + (conteos[3]*4) + (conteos[4]*5);
                        int totalRespuestas = conteos[0] + conteos[1] + conteos[2] + conteos[3] + conteos[4];
                        double promedio = (totalRespuestas == 0) ? 0 : sumaTotal / totalRespuestas;
                        
                        Row row = sheetPromedios.createRow(rowNumPromedios++);
                        row.createCell(0).setCellValue(encuesta.getTitulo());
                        row.createCell(1).setCellValue(pregunta);
                        row.createCell(2).setCellValue(String.format("%.2f", promedio));
                        row.createCell(3).setCellValue(totalRespuestas);
                    }
                }
            }

            // --- PESTAÑA 2: RESPUESTAS DETALLADAS ---
            XSSFSheet sheetDetallado = workbook.createSheet("Respuestas Detalladas");
            int rowNumDetallado = 0;
            
            Row headerDetallado = sheetDetallado.createRow(rowNumDetallado++);
            headerDetallado.createCell(0).setCellValue("Encuesta");
            headerDetallado.createCell(1).setCellValue("Usuario");
            headerDetallado.createCell(2).setCellValue("Pregunta");
            headerDetallado.createCell(3).setCellValue("Calificación");
            headerDetallado.createCell(4).setCellValue("Interpretación");

            for (RespuestaEncuesta re : todasLasRespuestas) {
                for (Respuesta r : re.getRespuestas()) { 
                    Row row = sheetDetallado.createRow(rowNumDetallado++);
                    row.createCell(0).setCellValue(re.getTituloEncuesta());
                    row.createCell(1).setCellValue(re.getNombreUsuario());
                    row.createCell(2).setCellValue(r.getTextoPregunta());
                    row.createCell(3).setCellValue(r.getPuntuacion());
                    row.createCell(4).setCellValue(getTextoLikert(r.getPuntuacion())); 
                }
            }
            
            sheetPromedios.autoSizeColumn(0);
            sheetPromedios.autoSizeColumn(1);
            sheetDetallado.autoSizeColumn(0);
            sheetDetallado.autoSizeColumn(1);
            sheetDetallado.autoSizeColumn(2);

            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                workbook.write(outputStream);
                return true; 
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false; 
        }
    }
    
    
  
   public static boolean generarPDFEncuesta(Encuesta encuesta, File archivo) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                
                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 16);
                contentStream.newLineAtOffset(50, 750);
                contentStream.showText("Reporte de Resultados Detallados");
                contentStream.endText();

                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 14);
                contentStream.newLineAtOffset(50, 730);
                contentStream.showText(encuesta.getTitulo());
                contentStream.endText();

                // --- CORRECCIÓN: Instanciar servicio ---
                RespuestaService respuestaService = new RespuestaService();
                
                ArrayList<RespuestaEncuesta> respuestas = respuestaService.getRespuestas().stream()
                        .filter(r -> r.getTituloEncuesta().equals(encuesta.getTitulo()))
                        .collect(Collectors.toCollection(ArrayList::new));

                if (respuestas.isEmpty()) {
                    contentStream.beginText();
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                    contentStream.newLineAtOffset(50, 700);
                    contentStream.showText("Esta encuesta aún no tiene respuestas.");
                    contentStream.endText();
                }

                float yPosition = 700;

                for (RespuestaEncuesta respuestaUsuario : respuestas) {
                    // Salto de página simple si se acaba el espacio
                    if (yPosition < 50) { 
                        contentStream.close(); // Cerrar stream actual
                        // Crear nueva página
                        PDPage newPage = new PDPage();
                        document.addPage(newPage);
                        // Iniciar nuevo stream (NO se puede hacer recursivo fácil aquí, pero esto evita el error)
                        // Para simplificar en este punto, solo cortamos la impresión o la reiniciamos en una versión más avanzada
                        break; 
                    }

                    contentStream.beginText();
                    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
                    contentStream.newLineAtOffset(50, yPosition);
                    contentStream.showText("Usuario: " + respuestaUsuario.getNombreUsuario());
                    contentStream.endText();
                    yPosition -= 20; 

                    for (Respuesta r : respuestaUsuario.getRespuestas()) {
                        String puntuacionTexto = getTextoLikert(r.getPuntuacion());
                        
                        contentStream.beginText();
                        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                        contentStream.newLineAtOffset(65, yPosition); 
                        contentStream.showText(r.getTextoPregunta() + " --- Calificación: " + puntuacionTexto); 
                        contentStream.endText();
                        yPosition -= 15;
                    }
                    yPosition -= 20; 
                }
            }
            
            document.save(archivo); 
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
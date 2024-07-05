/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.form;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author gerso
 */
public class Plantilla {

    String nombrePaciente;
    String apellidoPaciente;
    String fechaNacimiento;
    String edad;
    String cui;
    String residencia;
    String presion;
    String pulso;
    String temperatura;
    String peso;
    String talla;
    String consultaDoctora;
    String diagnostico;
    String sf;
    String fecha;
    String savePath; // Nueva variable para almacenar la ruta de guardado

    Document documento;
    FileOutputStream archivo;
    Paragraph titulo;

    public Plantilla(String nombrePaciente,
            String apellidoPaciente,
            String fechaNacimiento,
            String edad,
            String cui,
            String residencia,
            String presion,
            String pulso,
            String temperatura,
            String peso,
            String talla,
            String consultaDoctora,
            String diagnostico,
            String sf,
            String fecha,
            String savePath) {
        this.nombrePaciente = nombrePaciente;
        this.apellidoPaciente = apellidoPaciente;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.cui = cui;
        this.residencia = residencia;
        this.presion = presion;
        this.pulso = pulso;
        this.temperatura = temperatura;
        this.peso = peso;
        this.talla = talla;
        this.consultaDoctora = consultaDoctora;
        this.diagnostico = diagnostico;
        this.sf = sf;
        this.fecha = fecha;
        this.savePath = savePath;

        documento = new Document();
        titulo = new Paragraph("SF: " + sf);
    }

    public void crearPlantilla(boolean isModification) {

        try {

            // Crea el FileOutputStream utilizando el nombre del archivo
            archivo = new FileOutputStream(savePath);
            PdfWriter.getInstance(documento, archivo);
            documento.open();
            titulo.setAlignment(Element.ALIGN_CENTER);

            // Agregar el logotipo del ministerio de salud
            Image image = null;
            try {
                // Carga la imagen desde el paquete com.images
                image = Image.getInstance(getClass().getResource("/com/images/ministerio_logo.png"));
                image.scaleAbsolute(75, 75); // Cambio de tamaño
                image.setAbsolutePosition(475, 750); // Posicion de la imagen

            } catch (BadElementException | IOException e) {
            }

            documento.add(image);
            documento.add(titulo);

            documento.add(Chunk.NEWLINE);
            documento.add(Chunk.NEWLINE);

            // Alinear fecha a la derecha
            Paragraph fechaParagraph = new Paragraph("Fecha: " + fecha);
            fechaParagraph.setAlignment(Element.ALIGN_RIGHT);
            documento.add(fechaParagraph);

            documento.add(Chunk.NEWLINE);

            documento.add(new Paragraph("Nombre Paciente: " + nombrePaciente));
            documento.add(new Paragraph("Apellido Paciente: " + apellidoPaciente));
            documento.add(new Paragraph("Fecha de Nacimiento: " + fechaNacimiento));
            documento.add(new Paragraph("Edad: " + edad));
            documento.add(new Paragraph("CUI: " + cui));
            documento.add(new Paragraph("Residencia: " + residencia));

            documento.add(Chunk.NEWLINE);

            documento.add(new Paragraph("PRE CONSULTA"));

            documento.add(Chunk.NEWLINE);

            documento.add(new Paragraph("Presión: " + presion));
            documento.add(new Paragraph("Pulso: " + pulso));
            documento.add(new Paragraph("Temperatura: " + temperatura));
            documento.add(new Paragraph("Peso: " + peso));
            documento.add(new Paragraph("Talla: " + talla));

            documento.add(Chunk.NEWLINE);

            Paragraph diagnosticoParagraph = new Paragraph("Diagnóstico:\n" + diagnostico);
            diagnosticoParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
            documento.add(diagnosticoParagraph);

            documento.add(Chunk.NEWLINE);

            Paragraph consultaDoctoraParagraph = new Paragraph("Consulta Doctora:\n" + consultaDoctora);
            consultaDoctoraParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
            documento.add(consultaDoctoraParagraph);

            documento.close();
            
            // Mostrar mensaje de creación o modificación
            String mensaje = isModification ? "El archivo PDF se ha modificado correctamente!" : "El archivo PDF se ha creado correctamente!";
            JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException | DocumentException e) {
            System.err.println(e.getMessage());
        }
    }

}

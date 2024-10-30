/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hash;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
/**
 *
 * @author otaku
 */
public class Grafico {
    public void graficoColisao(DefaultCategoryDataset dataset){
        JFreeChart barChart = ChartFactory.createBarChart("Numero de Colisões", 
                "Tamanho do Vetor", 
                "Quantidade de Colisões", 
                dataset);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        JFrame frame = new JFrame();
        frame.setContentPane(chartPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public void graficoTempo(DefaultCategoryDataset dataset, int tamanho){
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Tempo de Execução Conjunto de: " + tamanho,
                "Tamanho da Tabela",
                "Tempo (ms)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        JFrame frame = new JFrame();
        frame.setContentPane(chartPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

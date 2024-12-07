package GUIs;

import DAOs.DAOCarro;
import DAOs.DAOLocacao;
import DAOs.DAOLocacaoHasCarro;
import DAOs.DAOPessoa;
import Entidades.Carro;
import Entidades.Cliente;
import Entidades.Locacao;
import Entidades.LocacaoHasCarro;
import Entidades.LocacaoHasCarroPK;
import Entidades.Pessoa;
import com.itextpdf.text.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.text.SimpleDateFormat;
import tools.GetDiretorioDaAplicacao;

public class LocacaoHasCarroPDF {

    public LocacaoHasCarroPDF(String nomeEntidade) {
        Document document = new Document();
        GetDiretorioDaAplicacao da = new GetDiretorioDaAplicacao();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(da.getDiretorioDaAplicacao() + "\\src\\PDFs\\" + nomeEntidade + "PDF" + ".pdf"));
            System.out.println(da.getDiretorioDaAplicacao() + "\\src\\PDFs\\" + nomeEntidade + "PDF" + ".pdf");

            document.open();

            document.setPageSize(PageSize.A4);

            Font f = new Font(FontFamily.COURIER, 15, Font.BOLD);
            Paragraph p1 = new Paragraph("RECIBO", f);

            p1.setAlignment(Element.ALIGN_CENTER);
            p1.setSpacingAfter(15);
            document.add(new Paragraph(p1));
            document.add(new Paragraph("  "));

            PdfPCell header = new PdfPCell();

            header.setBorderWidthBottom(1.0f);
            header.setBorder(Rectangle.AUTHOR);
            header.setBorder(Rectangle.BOTTOM);
            header.setBorder(Rectangle.LEFT);
            header.setColspan(2);
            PdfPTable table = new PdfPTable(7);

            table.addCell("Nome Pessoa");
            table.addCell("Nome do Carro");
            table.addCell("Data Locação");
            table.addCell("Data Final Locação");
            table.addCell("Preço");

            table.addCell("Quantidade Dias");
            table.addCell("Preço Por dia");

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            DAOLocacaoHasCarro daoLocacaoHasCarro = new DAOLocacaoHasCarro();
            DAOCarro daoCarro = new DAOCarro();
            DAOLocacao daoLocacao = new DAOLocacao();
            DAOPessoa daoPessoa = new DAOPessoa();

            List<LocacaoHasCarro> locacaoHasCarros = daoLocacaoHasCarro.list();
            for (LocacaoHasCarro locacaoHasCarro : locacaoHasCarros) {
                LocacaoHasCarroPK pk = locacaoHasCarro.getLocacaoHasCarroPK();

                if (pk != null) {
                    // Obter IDs a partir da PK
                    int locacaoId = pk.getLocacaoIdLocacao();
                    int carroId = pk.getCarroIdCarro();

                    // Buscar as entidades relacionadas
                    Locacao locacao = daoLocacao.obter(locacaoId);
                    Carro carro = daoCarro.obter(carroId);

                    long diffInMillis = locacao.getDataFinalLocacao().getTime() - locacao.getDataLocacao().getTime();
                    long diffInDays = diffInMillis / (24 * 60 * 60 * 1000); // Convertendo milissegundos para dias
                    
                    long precoPorDia = locacaoHasCarro.getPreco()/diffInDays;
                    
                    if (locacao != null) {
                        Cliente cliente = locacao.getClientePessoaCpfPessoa(); // Assumindo que Locacao tem um método getCliente()
                        if (cliente != null) {
                            String cpfPessoa = cliente.getPessoaCpfPessoa(); // Acessar o CPF dentro do Cliente
                            Pessoa pessoa = daoPessoa.obter(cpfPessoa);

                            // Adicionar os dados na tabela
                            table.addCell(pessoa != null ? pessoa.getNomePessoa() : "Desconhecido");
                            table.addCell(carro != null ? carro.getNomeCarro() : "Desconhecido");
                            table.addCell(sdf.format(locacao.getDataLocacao()));
                            table.addCell(sdf.format(locacao.getDataFinalLocacao()));
                            table.addCell(String.valueOf(locacaoHasCarro.getPreco()));
                            table.addCell(String.valueOf(diffInDays));
                            table.addCell(String.valueOf(precoPorDia));

                        }
                    }
                }
            }

            document.add(table);
            document.add(new Paragraph("  "));
        } catch (DocumentException | IOException de) {
            System.err.println(de.getMessage());
        }
        document.close();
    }
}

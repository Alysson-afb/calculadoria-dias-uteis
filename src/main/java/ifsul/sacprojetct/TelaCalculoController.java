package ifsul.sacprojetct;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.classes.DateUtils;
import model.classes.Feriado;
import model.services.FeriadoService;

public class TelaCalculoController implements Initializable {

    @FXML
    private Button btnCalcular;
    @FXML
    private Button btnDeletar;
    @FXML
    private Button btnSalvar;
    @FXML
    private TableColumn<Feriado, Date> tableColumnData;
    @FXML
    private TableColumn<Feriado, String> tableColumnDescricao;
    @FXML
    private TableView<Feriado> tableviewFeriados;
    @FXML
    private TextField txtData;
    @FXML
    private TextField txtDataFInal;
    @FXML
    private TextField txtDataInicial;
    @FXML
    private TextField txtDescricao;
    @FXML
    private TextField txtTotalDias;

    private ObservableList<Feriado> listaTabela = FXCollections.observableArrayList();
    private FeriadoService feriadoService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.feriadoService = new FeriadoService();

        tableColumnData.setCellValueFactory(new PropertyValueFactory<>("dia"));
        tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));

        tableviewFeriados.setItems(listaTabela);

        btnCalcular.setOnAction((t) -> {
            calcularDiasUteis();
            atualizarTabela();
        });

        btnSalvar.setOnAction((t) -> {
            salvarFeriado();
        });

        btnDeletar.setOnAction((t) -> {
            if (tableviewFeriados.getSelectionModel().getSelectedItem() != null) {
                Feriado feriado = tableviewFeriados.getSelectionModel().getSelectedItem();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmação");
                alert.setHeaderText(null);
                alert.setContentText(feriado.getDescricao() + " (" + DateUtils.dateToString(feriado.getDia()) + ") será excluído! Tem certeza?");
                if (alert.showAndWait().get() == ButtonType.OK) {
                    if (feriadoService.excluir(feriado)) {
                        Alert mens = new Alert(Alert.AlertType.INFORMATION);
                        mens.setTitle("Excluído");
                        mens.setHeaderText(null);
                        mens.setContentText("Registro excluído!");
                        mens.showAndWait();
                        atualizarTabela();
                    }
                }
            } else {
                showAlert(Alert.AlertType.WARNING, "Seleção Inválida", "Nenhum feriado selecionado.");
            }
        });

        atualizarTabela();
    }

    private void atualizarTabela() {
        List<Feriado> feriados = feriadoService.listar();
        this.listaTabela.clear();
        this.listaTabela.addAll(feriados);
    }

    private void salvarFeriado() {
        String dataStr = txtData.getText();
        String descricao = txtDescricao.getText();

        if (dataStr == null || dataStr.trim().equals("") || descricao == null || descricao.trim().equals("")) {
            showAlert(Alert.AlertType.WARNING, "Campos Inválidos", "Preencha a data e a descrição.");
            return;
        }

        Date data = DateUtils.stringToDate(dataStr);
        if (data == null) {
            showAlert(Alert.AlertType.ERROR, "Data Inválida", "Formato de data inválido. Use dd/MM/yyyy.");
            return;
        }

        Feriado feriado = new Feriado(data, descricao);

        if (feriadoService.inserir(feriado)) {
            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Feriado cadastrado!");

            atualizarTabela();

            txtData.clear();
            txtDescricao.clear();
        } else {
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao cadastrar feriado.");
        }
    }

    private void calcularDiasUteis() {
        Date dataInicial = DateUtils.stringToDate(txtDataInicial.getText());
        Date dataFinal = DateUtils.stringToDate(txtDataFInal.getText());

        if (dataInicial == null || dataFinal == null) {
            showAlert(Alert.AlertType.ERROR, "Datas Inválidas", "Verifique as datas inicial e final. Use dd/MM/yyyy.");
            return;
        }

        if (dataFinal.before(dataInicial)) {
            showAlert(Alert.AlertType.WARNING, "Período Inválido", "A data final deve ser posterior à data inicial.");
            return;
        }

        List<Feriado> feriados = feriadoService.listar();
        int diasUteis = DateUtils.getWorkingDays(dataInicial, dataFinal, feriados);
        txtTotalDias.setText(String.valueOf(diasUteis));
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

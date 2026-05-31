package model.services;

import java.util.List;
import model.DB.DB;
import model.classes.Feriado;
import model.dao.FeriadoDao;

public class FeriadoService {

    private FeriadoDao dao = new FeriadoDao();

    public boolean inserir(Feriado espaco) {
        return dao.inserir(espaco);
    }

    public boolean excluir(Feriado espaco) {
        return dao.excluir(espaco);
    }

    public List<Feriado> listar() {
        return dao.listar();
    }
}

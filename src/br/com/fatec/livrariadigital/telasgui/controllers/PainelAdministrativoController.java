
package br.com.fatec.livrariadigital.telasgui.controllers;

import br.com.fatec.livrariadigital.entidades.Informacoes;
import java.util.ArrayList;
import java.util.List;

public class PainelAdministrativoController extends ControllerGeral {
    public List<Integer> obterNumeroDeEditoras(){
        List<Informacoes> elemento=new ArrayList();
        elemento=this.consultaAvancada("Informacoes","id","0");
        List<Integer> dados=new ArrayList();
        dados.add(elemento.get(0).getNumEditoras());
        dados.add(elemento.get(0).getNumUsuarios());
        dados.add(elemento.get(0).getNumLivros());
        dados.add(elemento.get(0).getNumPedidos());
        return dados;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.como.mercadinhosantacruz.caixa.controllers;

import br.como.mercadinhosantacruz.caixa.models.Produto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author 20201114010016
 */
@Controller
public class CaixaController {
    
    @GetMapping("/")
    public String index(Model model){
        List<Produto> produtos = Produto.listar(jdbc);
        model.addAttribute("produtos", produtos);
        return "index";
    }
    
     @Autowired JdbcTemplate jdbc;
    
    @PostMapping("/adicionar")
    public String adicionar(String nome, int qtd, float preco){
        
        Produto p = new Produto(nome, qtd, preco);
        p.salvar(jdbc);
        return "redirect:/";
    }
    @GetMapping("/excluir")
    public String excluir(int id){
        Produto.remover(id, jdbc);
        return "redirect:/";
    }
    @GetMapping("/editar")
    public String editar (int id, Model model){
          List<Produto> produtos = Produto.listar(jdbc);
                  Produto p = Produto.buscar(id, jdbc);
        model.addAttribute("produtos", produtos);
        model.addAttribute("p", p);
        return "index";
    }
}

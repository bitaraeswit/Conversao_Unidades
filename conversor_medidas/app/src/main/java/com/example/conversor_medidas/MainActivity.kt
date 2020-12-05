package com.example.conversor_medidas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlin.Double as Double1


class MainActivity : AppCompatActivity() {
    //criação do array com as unidades a serem convertidas
    var unidades = arrayOf("Quilômetros(KM)", "Metros(M)", "Centímetros(CM)")

    //posicao selecionada no spinner
    var selecaoVlr       = 0
    var selecaoConversao = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Carregar os componentes
        val spinnerConversor= findViewById<Spinner>(R.id.selectConversor)
        val spinnerVlr      = findViewById<Spinner>(R.id.selectVlr)
        val txtResultado   = findViewById<TextView>(R.id.txtResultado)
        val txtValor        = findViewById<EditText>(R.id.txtValor)
        val btnCalcular      = findViewById<Button>(R.id.btnCalcular)


        //criação do array adapter (recebe contexto, layout, array )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,unidades)


        //insere os itens no spinner
        spinnerConversor.adapter = adapter
        spinnerVlr.adapter       = adapter

        //pega o item selecionado no spinner valor
        spinnerVlr.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) { // obrigatoria caso não selecione nenhum item
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selecaoVlr = p2; // posicao do vetor
            }
        }
        //pega o item selecionado no spinner conversao
        spinnerConversor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) { // obrigatoria caso não selecione nenhum item
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                selecaoConversao = p2;// posicao do vetor
            }
        }

        //cálculo de conversões
        btnCalcular.setOnClickListener{
            val valor = txtValor.text.toString();
            var texto = ""

            if (valor !=""){
                if(selecaoVlr == 0){ // km
                    if (selecaoConversao == 1) { // metros
                        texto = "Resultado: "
                        texto += Double.parseDouble(valor) * 1000f
                        texto += " metros"

                        txtResultado.text = texto
                    } else if (selecaoConversao == 2) {// cm
                        texto = "Resultado: "
                        texto += Double.parseDouble(valor) * 100000f
                        texto += " centímetros"

                        txtResultado.text = texto
                    }else {
                        txtResultado.text = "Resultado: " + valor + " Km"// KM
                    }

                }else if(selecaoVlr == 1){ //metros
                        if (selecaoConversao == 0) { // KM
                            texto = "Resultado: "
                            texto += Double.parseDouble(valor) / 1000f
                            texto += " Km"

                            txtResultado.text = texto
                        } else if (selecaoConversao == 2) {// cm
                            texto = "Resultado: "
                            texto += Double.parseDouble(valor) * 100f
                            texto += " centímetros"

                            txtResultado.text = texto
                        }else {
                            txtResultado.text = "Resultado: " + valor + " metros" // Metros
                        }

                }else if (selecaoVlr == 2) { // CM
                    if (selecaoConversao == 0) { // KM
                        texto = "Resultado: "
                        texto += Double.parseDouble(valor) / 100000f
                        texto += " Km"

                        txtResultado.text = texto
                    } else if (selecaoConversao == 1) {// Metros
                        texto = "Resultado: "
                        texto += Double.parseDouble(valor) / 100f
                        texto += " metros"

                        txtResultado.text = texto
                    }else {
                        txtResultado.text = "Resultado: " + valor + " cm" // Cm
                    }
                }

            }else {
                txtResultado.text = "Adicione um valor de comprimento!"
            }
        }

    }

}
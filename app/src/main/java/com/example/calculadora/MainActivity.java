package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //definindo referências para os botões/painel
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven ;
    private Button eight;
    private Button nine ;
    private Button zero;

    private Button plus;
    private Button sub;
    private Button mult;
    private Button div;
    private Button common;
    private Button mod;
    private Button equal ;
    private Button ac;
    private TextView panel;

    private boolean lastOperator = false;
    private boolean hasDecimal = false;

    //Definindo referencia para os Listeners dos botões
    private handlerNumbers numbersListener;
    private handlerOperators operatorsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Pega as referencias dos botões pelos Identificadores.
        one = findViewById(R.id.OneBtn);
        two = findViewById(R.id.TwoBtn);
        three = findViewById(R.id.ThreeBtn);
        four = findViewById(R.id.FourBtn);
        five = findViewById(R.id.FiveBtn);
        six = findViewById(R.id.SixBtn);
        seven = findViewById(R.id.SevenBtn);
        eight = findViewById(R.id.EightBtn);
        nine = findViewById(R.id.NineBtn);
        zero = findViewById(R.id.ZeroBtn);

        plus = findViewById(R.id.PlusBtn);
        sub = findViewById(R.id.SubBtn);
        mult = findViewById(R.id.multBtn);
        div = findViewById(R.id.DivisionBtn);
        common = findViewById(R.id.CommonBtn);
        mod = findViewById(R.id.ModuleBtn);
        equal = findViewById(R.id.EqualBtn);
        ac = findViewById(R.id.clearBtn);

        panel = findViewById(R.id.panel);

        //Instanciando os Listeners.
        numbersListener = new handlerNumbers();
        operatorsListener = new handlerOperators();

        //Adicionando os Listeners para os botões
        one.setOnClickListener(numbersListener);
        two.setOnClickListener(numbersListener);
        three.setOnClickListener(numbersListener);
        four.setOnClickListener(numbersListener);
        five.setOnClickListener(numbersListener);
        six.setOnClickListener(numbersListener);
        seven.setOnClickListener(numbersListener);
        eight.setOnClickListener(numbersListener);
        nine.setOnClickListener(numbersListener);
        zero.setOnClickListener(numbersListener);

        common.setOnClickListener(operatorsListener);
        plus.setOnClickListener(operatorsListener);
        sub.setOnClickListener(operatorsListener);
        mult.setOnClickListener(operatorsListener);
        div.setOnClickListener(operatorsListener);
        mod.setOnClickListener(operatorsListener);
        equal.setOnClickListener(operatorsListener);

        ac.setOnClickListener(new handlerClear());

    }

    //verifica se um caracter corresponde a um número ou uma vírgula(.)
    public boolean isNumber(char v){
       try{
           int value = Integer.parseInt(String.valueOf(v));
           return true;
       }
       catch(NumberFormatException e){
           return v=='.';
       }
    }

    //Dada uma expressão em forma de cadeia de caracteres, adiciona o texto ao painel de visualização
    public void setResults(CharSequence expression){
       int position=0,begin;
       double first,second;
       char operator,lido;

       //Adquirindo o primeiro número da expressão.
       while(isNumber(expression.charAt(position)))
           position++;

       first = Double.parseDouble(expression.subSequence(0, position).toString());
       operator = expression.charAt(position);
       position++;

       /* Em cada interação, adquire-se o segundo numero da expressão e realiza a operação
        com o primeiro numero, definindo um novo primeiro numero atraves do resultado. */
       while(position<expression.length()){
           lido = expression.charAt(position);
           begin = position;
           while(isNumber(lido) && lido!='='){
               position++;
               lido =  expression.charAt(position);
           }
           second = Double.parseDouble(expression.subSequence(begin,position).toString());
           switch(operator){
               case '+':{
                   first += second;
                   break;
               }
               case '-':{
                   first -= second;
                   break;
               }
               case 'x':{
                   first *= second;
                   break;
               }
               case '÷':{
                   first /= second;
                   break;
               }
               case '%':{
                   first %= second;
                   break;
               }
               default:{
                   break;
               }
           }
           operator = expression.charAt(position); position++;
       }

       panel.setText(String.valueOf(first));

    }

    //Definindo classes internas para herdar de OnclickListener e sobreescrever o método executado ao clicar nos botões
    public class handlerNumbers implements View.OnClickListener{
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View v) {
            panel.setText(panel.getText().toString() + ((Button)v).getText().toString());
            lastOperator = false;
        }
    }

    public class handlerOperators implements View.OnClickListener{
        @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
        @Override
        public void onClick(View v) {
            CharSequence value = panel.getText();

            if(!value.equals("") && !lastOperator){

                switch(v.getId()){
                    case R.id.EqualBtn:{
                        panel.setText(value + "=");
                        setResults(panel.getText());
                        break;
                    }
                    case R.id.CommonBtn:{
                        if(!hasDecimal){
                            panel.setText(value + ".");
                            lastOperator = true;
                            hasDecimal = true;
                        }
                        break;
                    }
                    default:{
                        panel.setText(value + ((Button)v).getText().toString());
                        lastOperator = true;
                        hasDecimal = false;
                        break;
                    }
                }

            }
        }
    }

    public class handlerClear implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            panel.setText("");
        }
    }

}
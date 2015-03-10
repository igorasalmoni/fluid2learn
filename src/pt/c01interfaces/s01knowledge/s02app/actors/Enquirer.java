package pt.c01interfaces.s01knowledge.s02app.actors;

import pt.c01interfaces.s01knowledge.s01base.impl.BaseConhecimento;


import pt.c01interfaces.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IDeclaracao;
import pt.c01interfaces.s01knowledge.s01base.inter.IEnquirer;
import pt.c01interfaces.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IResponder;

public class Enquirer implements IEnquirer
{
    IObjetoConhecimento obj;
    String[] perguntas, respostas;
    
    
    public Enquirer() {
        perguntas = new String[42];
        respostas = new String[42];
    }

    public String Ask (String pergunta,IResponder responder) {
    	int i;
    	for (i = 0; i < perguntas.length && perguntas[i] != null; i++){
    		if (pergunta.equalsIgnoreCase(perguntas[i]))
    			break;
    	}
    	
    	if(perguntas[i] == null){
    		perguntas[i] = pergunta;
    		respostas[i] = responder.ask(pergunta);
    	}
    	
    	return respostas[i];   		
}
	

	public void connect(IResponder responder)
	{
        IBaseConhecimento bc = new BaseConhecimento();
		String [] animals = {"tiranossauro", "aranha", "camarao", "humano", "pikachu"};

		
		int i;
		boolean animalEsperado = false;

		
		for (i = 0; i < animals.length && !animalEsperado; i++){

			obj = bc.recuperaObjeto(animals[i]);
			IDeclaracao decl = obj.primeira();
			animalEsperado = true;
			
			while (decl != null && animalEsperado) {
			
				String pergunta = decl.getPropriedade();
				String respostaEsperada = decl.getValor();
				String resposta = Ask (pergunta, responder);
				
				if (resposta.equalsIgnoreCase(respostaEsperada))
					decl = obj.proxima();
				else
					animalEsperado = false;
			}
			
			
		}
		boolean acertei = responder.finalAnswer(animals[i-1]);
		
		if (acertei)
			System.out.println("Oba! Acertei!");
		else
			System.out.println("fuem! fuem! fuem!");
	}
}

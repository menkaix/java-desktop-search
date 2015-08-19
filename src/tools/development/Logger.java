package tools.development;
/**
 * Cette détecte l'origine directe d'une exception (fichier source et ligne) ainsi que les messages d'erreur
 * et d'autre informations
 * @author mendrika
 *
 */
public class Logger {
	
	
	/**
	 * Afficche le ficher et la ligne de l'erreur
	 * @param e L'erreur ou l'exception à rapporter
	 * @param comments (optionnel) Commentaire à afficher en plus du message
	 */
	public static void error(Throwable e, String...comments)
	{
		
		StackTraceElement[] stack = e.getStackTrace();
		
		StackTraceElement elt = stack[0];
		
		if(comments.length>0){
			System.out.println("[E] -> "+elt.getFileName()+" l."+elt.getLineNumber()+" : "+e.getClass().getSimpleName()+":"+e.getMessage()+" - "+comments[0]);
		}
		else{
			System.out.println("[E] -> "+elt.getFileName()+" l."+elt.getLineNumber()+" : "+e.getClass().getSimpleName()+":"+e.getMessage());
		}
		
	}
	
	/**
	 * Imprime un message à l'&eacute;cran précédé du fichier et de la ligne o&ugrave; la commande
	 * est lanc&eacute;e avec un log level Info
	 * @param comments
	 */
	public static void info(String...comments)
	{
		tag("I", comments);
	}
	
	
	/**
	 * Imprime un message à l'&eacute;cran précédé du fichier et de la ligne o&ugrave; la commande
	 * est lanc&eacute;e avec un log level Warning
	 * @param comments
	 */
	public static void warning(String...comments)
	{
		tag("W", comments);
	}
	
	
	
	public static void tag(String tag,String...comments)
	{
		
		if(comments.length<1){
			return ;
		}
		
		try{
			StackTraceElement[] stack = Thread.currentThread().getStackTrace();
			
			StackTraceElement elt = stack[2];
			
			System.out.println("["+tag+"] -> "+elt.getFileName()+" l."+elt.getLineNumber()+" : "+comments[0]);
			
		}catch(ArrayIndexOutOfBoundsException e){
			Logger.error(e, "Dans le Logger !!!!");
		}
		
	}

}

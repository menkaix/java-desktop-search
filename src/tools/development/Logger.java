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
		
		
		System.out.println(stringError(e, comments));
		
		
	}
	
	public static String stringError(Throwable e, String...comments)
	{
		StackTraceElement[] stack = e.getStackTrace();
		
		int lineNumber ;
		String fileName ;
		
		int i=-1 ;
		
		do{
		StackTraceElement elt = stack[++i];
		
		lineNumber = elt.getLineNumber() ;
		fileName = elt.getFileName() ;
		
		if (i!=0){
			fileName = "* "+fileName;
		}
		
		}while (lineNumber==-1);
		
		if(comments.length>0 && comments!=null){
			return ("[E] -> "+fileName+" l."+lineNumber+" : "+e.getClass().getSimpleName()+":"+e.getMessage()+" - "+comments[0]);
		}
		else{
			return("[E] -> "+fileName+" l."+lineNumber+" : "+e.getClass().getSimpleName()+":"+e.getMessage());
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

Arena ABM Builder
===============

Framework for creating an ABM with Arena-UI framework





<h2>Como usar:</h2>

Se necesita una clase que herede de ABMapplicationModel  (en donde van a ir las annotations de la interfaz de usuario), otra que herede de ABMbuilder (en donde solamente se define el new para especificar la clase del ApplicationModel).

Para correrlo siempre va a ser necesario una del tipo
>
>```java
>public class TestApplication extends Application{
>
>	@SuppressWarnings("serial")
>	@Override
>	protected Window<?> createMainWindow() {
>		return new ClaseQueExtiendaDeABMbuilder(this,new ClaseDelDominioQueExtiendaApplicationModel()) {
>		};
>	}
>
>	public static void main(String[] args) {
>		new TestApplication().start();
>	}
>```

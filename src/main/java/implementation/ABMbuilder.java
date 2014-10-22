package implementation;

import java.lang.reflect.Method;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.Action;

import annotations.abm.AddMethod;
import annotations.abm.Title;

@SuppressWarnings("serial")
public abstract class ABMbuilder extends SimpleWindow<ObjectABM> {

	public ABMbuilder(WindowOwner parent, ObjectABM model) {
		super(parent, model);

	}

	protected void addButtons(Panel actionsPanel) {

		new Button(actionsPanel).setCaption("Nuevo");

		new Button(actionsPanel).setCaption("Editar");

		new Button(actionsPanel).setCaption("Eliminar");

	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {

		// Pone el titulo sacado de la annotation

		ObjectABM objetoDeDominio = this.getModelObject();

		Title titleAnnotation = objetoDeDominio.getClass()
				.getDeclaredAnnotation(Title.class);
		this.setTitle(titleAnnotation.title());

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		/*Class<? extends ObjectABM> claseDominio = this.getModelObject()
				.getClass();

		for (Method method : claseDominio.getMethods()) {

			if (method.isAnnotationPresent(AddMethod.class)) {
				new Button(mainPanel).setCaption("Nuevo").onClick(
						((Action) Exceptions.soften(() -> method.invoke(this, (Object[]) null))));
			}

		}
*/
	}

	

	@Override
	protected void addActions(Panel mainPanel) {

	}
}


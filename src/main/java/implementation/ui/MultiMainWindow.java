package implementation.ui;

import implementation.FWObject;
import implementation.applicationModel.ActionOpen;
import implementation.applicationModel.MultiABMApplicationModel;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

public class MultiMainWindow extends SimpleWindow<MultiABMApplicationModel> {

	public MultiMainWindow(WindowOwner parent,
			MultiABMApplicationModel MultiABMApplicationModel) {
		super(parent, MultiABMApplicationModel);
	}

	@Override
	protected void addActions(Panel actionsPanel) {

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {

		setTitle("Seleccion de ABM");

		Panel botons = new Panel(mainPanel);
		botons.setLayout(new VerticalLayout());

		for (Class<? extends FWObject> clase: this.getModelObject().domainClasses) {
			Button button = new Button(botons);
			button.setCaption(clase.getSimpleName());
			button.onClick(new ActionOpen(this, clase)).setWidth(150);
		}

	}

}

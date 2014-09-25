package implementation;

import org.uqbar.arena.bindings.NotNullObservable;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;


@SuppressWarnings("serial")
public abstract class ABMbuilder extends SimpleWindow<ABMapplicationModel> {

	
	public ABMbuilder(WindowOwner parent, ABMapplicationModel model, String title) {
		super(parent, model);
		model.setWindowTitle(title);
	}

	@SuppressWarnings({ "unchecked" })
	protected void addButtons(Panel actionsPanel) {
		
		new Button(actionsPanel)
			.setCaption("Nuevo")
			//.onClick(new addWindow(this, this.getModelObject()).open())
			;

		NotNullObservable elementSelected = new NotNullObservable("objectSelected");
		
		new Button(actionsPanel)
			.setCaption("Editar")
			.bindEnabled(elementSelected)
			//.onClick(new editWindow(this, this.getModelObject().getObjectSelected()).open())
			;

		new Button(actionsPanel)
			.setCaption("Eliminar")
			.bindEnabled(elementSelected)
			//.onClick(new removeWindow(this, this.getModelObject().getObjectSelected()).open())
			;

	}
	
	@Override
	protected void createMainTemplate(Panel mainPanel) {

		mainPanel.setLayout(new VerticalLayout());
		super.createMainTemplate(mainPanel);
		
		Panel componentPanel = new Panel(mainPanel);
		componentPanel.setLayout(new HorizontalLayout());
		this.addTable(componentPanel);
		
		Panel buttonsPanel = new Panel(componentPanel);
		buttonsPanel.setLayout(new VerticalLayout());
		this.addButtons(buttonsPanel);

	}
	
	private void addTable(Panel componentPanel) {
//		Table<Class> table = new Table<Class>(mainPanel, Class.class);
//		table.setHeigth(:/);
//		table.setWidth(:/);
//		table.bindItemsToProperty(Objects);
//		table.bindValueToProperty("objectSeleccionado");
//		
//		for :
//			
//			new Column<Class>(table)
//			.setTitle(Title)
//			.setFixedSize(225)
//			.bindContentsToProperty(Attr);

	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle((this.getModelObject()).getWindowTitle());
	}
	
	@Override
	protected void addActions(Panel mainPanel) {}
}

package gui.formation;

import javax.swing.JPanel;

import builders.formation.FormationTemplate;

public abstract class FormationTemplatePanel<FT extends FormationTemplate> extends JPanel
{
	private static final long serialVersionUID = -1572635059590322744L;

	public abstract FT getFormationTemplate();

	public abstract void setFormationTemplate(FT template);
}

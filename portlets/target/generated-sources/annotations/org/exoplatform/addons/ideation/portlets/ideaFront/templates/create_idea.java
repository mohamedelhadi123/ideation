package org.exoplatform.addons.ideation.portlets.ideaFront.templates;
import juzu.impl.plugin.template.metadata.TemplateDescriptor;
import juzu.impl.plugin.template.TemplateService;
@javax.annotation.Generated({})
public class create_idea extends juzu.template.Template
{
@javax.inject.Inject
public create_idea(TemplateService templatePlugin)
{
super(templatePlugin, "/org/exoplatform/addons/ideation/portlets/ideaFront/templates/create_idea.gtmpl");
}
public static final juzu.impl.plugin.template.metadata.TemplateDescriptor DESCRIPTOR = new juzu.impl.plugin.template.metadata.TemplateDescriptor("/org/exoplatform/addons/ideation/portlets/ideaFront/templates/create_idea.gtmpl",0x96c91c4807293b64L,org.exoplatform.addons.ideation.portlets.ideaFront.templates.create_idea.class,juzu.impl.template.spi.juzu.dialect.gtmpl.GroovyTemplateStub.class);
public Builder builder() {
return new Builder();
}
public Builder with() {
return (Builder)super.with();
}
public class Builder extends juzu.template.Template.Builder
{
}
}

package org.exoplatform.addons.ideation.portlets.ideationAdministration.templates;
import juzu.impl.plugin.template.metadata.TemplateDescriptor;
import juzu.impl.plugin.template.TemplateService;
@javax.annotation.Generated({})
public class index extends juzu.template.Template
{
@javax.inject.Inject
public index(TemplateService templatePlugin)
{
super(templatePlugin, "/org/exoplatform/addons/ideation/portlets/ideationAdministration/templates/index.gtmpl");
}
public static final juzu.impl.plugin.template.metadata.TemplateDescriptor DESCRIPTOR = new juzu.impl.plugin.template.metadata.TemplateDescriptor("/org/exoplatform/addons/ideation/portlets/ideationAdministration/templates/index.gtmpl",0x8690179175d2338dL,org.exoplatform.addons.ideation.portlets.ideationAdministration.templates.index.class,juzu.impl.template.spi.juzu.dialect.gtmpl.GroovyTemplateStub.class);
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

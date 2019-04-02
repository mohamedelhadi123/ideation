package org.exoplatform.addons.ideation.portlets.ideaFront;
import juzu.impl.plugin.application.descriptor.ApplicationDescriptor;
public class Application {
private final ApplicationDescriptor descriptor;
public Application() throws Exception {
this.descriptor = ApplicationDescriptor.create(getClass());
}
public ApplicationDescriptor getDescriptor() {
return descriptor;
}
}

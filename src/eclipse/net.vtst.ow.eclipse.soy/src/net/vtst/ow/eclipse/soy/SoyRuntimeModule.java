/*
 * generated by Xtext
 */
package net.vtst.ow.eclipse.soy;

import net.vtst.eclipse.easyxtext.guice.EasyXtextModule;
import net.vtst.ow.eclipse.soy.parser.CustomizedSoyLexer;
import net.vtst.ow.eclipse.soy.parser.SoyValueConverterService;
import net.vtst.ow.eclipse.soy.parser.antlr.internal.InternalSoyLexer;
import net.vtst.ow.eclipse.soy.resource.SoyLocationInFileProvider;
import net.vtst.ow.eclipse.soy.resource.SoyResourceDescriptionStrategy;
import net.vtst.ow.eclipse.soy.scoping.SoyBuiltinGlobalScopeProvider;
import net.vtst.ow.eclipse.soy.scoping.SoyScopeProvider;

import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;
import org.eclipse.xtext.resource.ILocationInFileProvider;
import org.eclipse.xtext.resource.containers.StateBasedContainerManager;

import com.google.inject.Binder;
import com.google.inject.Provider;

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class SoyRuntimeModule extends net.vtst.ow.eclipse.soy.AbstractSoyRuntimeModule {

  public static String PLUGIN_ID = "net.vtst.ow.eclipse.soy";

  public void configure(Binder binder) {
    super.configure(binder);
    binder.install(new EasyXtextModule());
    binder.requestStaticInjection(CustomizedSoyLexer.class);
  }
  
  // Parsing
  
  @Override
  public Class<? extends org.eclipse.xtext.parser.antlr.Lexer> bindLexer() {
    return net.vtst.ow.eclipse.soy.parser.CustomizedSoyLexer.class;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Provider<InternalSoyLexer> provideInternalSoyLexer() {
    return (org.eclipse.xtext.parser.antlr.LexerProvider.create((Class<InternalSoyLexer>) CustomizedSoyLexer.class.asSubclass(InternalSoyLexer.class)));
  }
  
  @Override
  public Class<? extends IValueConverterService> bindIValueConverterService() {
    return SoyValueConverterService.class;
  }
  
  // Outline
  
  public Class<? extends ILocationInFileProvider> bindILocationInFileProvider() {
    return SoyLocationInFileProvider.class;
  }
  
  // Scoping
  
  @Override
  // This is like the default one, but I keep it for memory
  public Class<? extends IContainer.Manager> bindIContainer$Manager() {
    return StateBasedContainerManager.class;
  }

  public Class<? extends IDefaultResourceDescriptionStrategy> bindIDefaultResourceDescriptionStrategy() {
    return SoyResourceDescriptionStrategy.class;
  }

  public void configureIGlobalScopeProviderDelegate(com.google.inject.Binder binder) {
    binder.bind(org.eclipse.xtext.scoping.IGlobalScopeProvider.class).
    annotatedWith(com.google.inject.name.Names.named(
        SoyScopeProvider.GLOBAL_SCOPE_PROVIDER_WITH_BUILTINS
    )).to(SoyBuiltinGlobalScopeProvider.class);}


}
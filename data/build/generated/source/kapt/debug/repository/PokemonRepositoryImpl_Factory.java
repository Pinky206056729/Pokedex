package repository;

import com.siphokazi.pokedex.apiCall.PokeApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class PokemonRepositoryImpl_Factory implements Factory<PokemonRepositoryImpl> {
  private final Provider<PokeApiService> apiServiceProvider;

  private PokemonRepositoryImpl_Factory(Provider<PokeApiService> apiServiceProvider) {
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public PokemonRepositoryImpl get() {
    return newInstance(apiServiceProvider.get());
  }

  public static PokemonRepositoryImpl_Factory create(Provider<PokeApiService> apiServiceProvider) {
    return new PokemonRepositoryImpl_Factory(apiServiceProvider);
  }

  public static PokemonRepositoryImpl newInstance(PokeApiService apiService) {
    return new PokemonRepositoryImpl(apiService);
  }
}

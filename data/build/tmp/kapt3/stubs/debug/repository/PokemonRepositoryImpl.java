package repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\nJ\"\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u00062\u0006\u0010\u000e\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\tH\u0082@\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lrepository/PokemonRepositoryImpl;", "Lcom/siphokazi/pokedex/domain/repository/PokemonRepository;", "apiService", "Lcom/siphokazi/pokedex/apiCall/PokeApiService;", "(Lcom/siphokazi/pokedex/apiCall/PokeApiService;)V", "getPokemonDetail", "Lcom/siphokazi/pokedex/domain/common/Result;", "Lcom/siphokazi/pokedex/domain/model/PokemonDetail;", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPokemonList", "", "Lcom/siphokazi/pokedex/domain/model/PokemonListItem;", "limit", "getPrimaryPokemonType", "", "pokemonId", "data_debug"})
public final class PokemonRepositoryImpl implements com.siphokazi.pokedex.domain.repository.PokemonRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.siphokazi.pokedex.apiCall.PokeApiService apiService = null;
    
    @javax.inject.Inject()
    public PokemonRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.siphokazi.pokedex.apiCall.PokeApiService apiService) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getPokemonList(int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.siphokazi.pokedex.domain.common.Result<? extends java.util.List<com.siphokazi.pokedex.domain.model.PokemonListItem>>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getPokemonDetail(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.siphokazi.pokedex.domain.common.Result<com.siphokazi.pokedex.domain.model.PokemonDetail>> $completion) {
        return null;
    }
    
    private final java.lang.Object getPrimaryPokemonType(int pokemonId, kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
}
package com.siphokazi.pokedex.apiCall;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\n"}, d2 = {"Lcom/siphokazi/pokedex/apiCall/PokeApiService;", "", "getPokemonDetail", "Lremote/response/PokemonDetailResponse;", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPokemonList", "Lremote/response/PokemonListResponse;", "limit", "data_debug"})
public abstract interface PokeApiService {
    
    @retrofit2.http.GET(value = "pokemon")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPokemonList(@retrofit2.http.Query(value = "limit")
    int limit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super remote.response.PokemonListResponse> $completion);
    
    @retrofit2.http.GET(value = "pokemon/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPokemonDetail(@retrofit2.http.Path(value = "id")
    int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super remote.response.PokemonDetailResponse> $completion);
}
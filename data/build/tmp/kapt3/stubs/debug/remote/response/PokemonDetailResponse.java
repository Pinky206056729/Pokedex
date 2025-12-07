package remote.response;

/**
 * The main DTO for the detailed Pokémon endpoint (e.g., /pokemon/1).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u000b\u00a2\u0006\u0002\u0010\u0011J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\tH\u00c6\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00c6\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000bH\u00c6\u0003J\u000f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00100\u000bH\u00c6\u0003Jk\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u000bH\u00c6\u0001J\u0013\u0010\'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010*\u001a\u00020\u0003H\u00d6\u0001J\t\u0010+\u001a\u00020\u0005H\u00d6\u0001R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015\u00a8\u0006,"}, d2 = {"Lremote/response/PokemonDetailResponse;", "", "id", "", "name", "", "height", "weight", "sprites", "Lremote/response/SpritesResponse;", "stats", "", "Lremote/response/StatResponse;", "types", "Lremote/response/TypeResponse;", "abilities", "Lremote/response/AbilityResponse;", "(ILjava/lang/String;IILremote/response/SpritesResponse;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getAbilities", "()Ljava/util/List;", "getHeight", "()I", "getId", "getName", "()Ljava/lang/String;", "getSprites", "()Lremote/response/SpritesResponse;", "getStats", "getTypes", "getWeight", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "data_debug"})
public final class PokemonDetailResponse {
    private final int id = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    private final int height = 0;
    private final int weight = 0;
    @org.jetbrains.annotations.NotNull()
    private final remote.response.SpritesResponse sprites = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<remote.response.StatResponse> stats = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<remote.response.TypeResponse> types = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<remote.response.AbilityResponse> abilities = null;
    
    public PokemonDetailResponse(int id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, int height, int weight, @org.jetbrains.annotations.NotNull()
    remote.response.SpritesResponse sprites, @org.jetbrains.annotations.NotNull()
    java.util.List<remote.response.StatResponse> stats, @org.jetbrains.annotations.NotNull()
    java.util.List<remote.response.TypeResponse> types, @org.jetbrains.annotations.NotNull()
    java.util.List<remote.response.AbilityResponse> abilities) {
        super();
    }
    
    public final int getId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    public final int getHeight() {
        return 0;
    }
    
    public final int getWeight() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final remote.response.SpritesResponse getSprites() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<remote.response.StatResponse> getStats() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<remote.response.TypeResponse> getTypes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<remote.response.AbilityResponse> getAbilities() {
        return null;
    }
    
    public final int component1() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final int component4() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final remote.response.SpritesResponse component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<remote.response.StatResponse> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<remote.response.TypeResponse> component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<remote.response.AbilityResponse> component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final remote.response.PokemonDetailResponse copy(int id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, int height, int weight, @org.jetbrains.annotations.NotNull()
    remote.response.SpritesResponse sprites, @org.jetbrains.annotations.NotNull()
    java.util.List<remote.response.StatResponse> stats, @org.jetbrains.annotations.NotNull()
    java.util.List<remote.response.TypeResponse> types, @org.jetbrains.annotations.NotNull()
    java.util.List<remote.response.AbilityResponse> abilities) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}
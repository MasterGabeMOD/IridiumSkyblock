package com.iridium.iridiumskyblock.database;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Represents a User of IridiumSkyblock.
 */
@Getter
@Setter
@NoArgsConstructor
@DatabaseTable(tableName = "users")
public final class User {

    @DatabaseField(columnName = "uuid", canBeNull = false, id = true)
    private @NotNull UUID uuid;

    @DatabaseField(columnName = "name", canBeNull = false)
    private @NotNull String name;

    @DatabaseField(columnName = "island_id")
    private @Nullable Integer island;

    @DatabaseField(columnName = "join_time")
    private @NotNull Long joinTime;

    public @NotNull Optional<Island> getIsland() {
        if (island == null) return Optional.empty();
        return IridiumSkyblock.getInstance().getDatabaseManager().getIslandById(island);
    }

    public void setIsland(@Nullable Island island) {
        this.island = island == null ? null : island.getId();
    }

    public LocalDateTime getJoinTime() {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(joinTime), ZoneId.systemDefault());
    }

    public void setJoinTime(LocalDateTime joinTime) {
        this.joinTime = ZonedDateTime.of(joinTime, ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * The default constructor.
     *
     * @param uuid The UUID of the {@link org.bukkit.entity.Player}
     * @param name The name of the Player
     */
    public User(final @NotNull UUID uuid, final @NotNull String name) {
        this.uuid = uuid;
        this.name = name;
        this.joinTime = 0L;
    }

}

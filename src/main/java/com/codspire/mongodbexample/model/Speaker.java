package com.codspire.mongodbexample.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bson.types.ObjectId;
import org.immutables.criteria.Criteria;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@JsonSerialize(as = ImmutableSpeaker.class)
@JsonDeserialize(as = ImmutableSpeaker.class)
@Value.Immutable
@Criteria // generate criteria
@Criteria.Repository // means generate repository (different from @Criteria)
public abstract class Speaker {

    @Criteria.Id
    @Value.Default
    @JsonProperty("_id")
    ObjectId speakerId() {
        return ObjectId.get();
    }

    abstract String firstName();

    abstract String lastName();

    abstract String email();

    @Nullable
    abstract String twitter();

}

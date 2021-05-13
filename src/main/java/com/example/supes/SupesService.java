package com.example.supes;

import com.example.supes.grpc.MutinySupesServiceGrpc;
import com.example.supes.grpc.Supes;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.inject.Singleton;

@Singleton
public class SupesService extends MutinySupesServiceGrpc.SupesServiceImplBase {

    @Override
    public Uni<Supes.Hero> getRandomHero(Supes.Empty request) {
        return Hero.findRandom().map(h -> Supes.Hero.newBuilder().setImage(h.image).setLevel(h.level).setName(h.name).build());
    }

    @Override
    public Uni<Supes.Villain> getRandomVillain(Supes.Empty request) {
        return Villain.findRandom().map(v -> Supes.Villain.newBuilder().setImage(v.image).setLevel(v.level).setName(v.name).build());
    }

    @Override
    public Multi<Supes.Hero> getHeroes(Supes.Empty request) {
        return Hero.findAll().stream()
                .onItem().castTo(Hero.class)
                .map(h -> Supes.Hero.newBuilder().setImage(h.image).setLevel(h.level).setName(h.name).build());
    }

    @Override
    public Multi<Supes.Villain> getVillains(Supes.Empty request) {
        return Villain.findAll().stream()
                .onItem().castTo(Villain.class)
                .map(v -> Supes.Villain.newBuilder().setImage(v.image).setLevel(v.level).setName(v.name).build());
    }
}

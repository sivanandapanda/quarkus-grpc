package com.example.fight;

import com.example.supes.grpc.FightServiceOuterClass;
import com.example.supes.grpc.MutinyFightServiceGrpc;
import com.example.supes.grpc.Supes;
import io.quarkus.grpc.runtime.annotations.GrpcService;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/fight")
public class FightResource {

    @Inject @GrpcService("fight-service") MutinyFightServiceGrpc.MutinyFightServiceStub fightService;

    @GET
    @Produces("application/json")
    public Uni<FightServiceOuterClass.Fight> triggerFight() {
        return fightService.fight(Supes.Empty.newBuilder().build());

    }
}
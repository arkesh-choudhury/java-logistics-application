package com.example.services;

import com.example.models.DeliveryAgent;
import com.example.storage.JsonStorage;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;

public class AgentService {
    private final JsonStorage<DeliveryAgent> agentStorage;
    private List<DeliveryAgent> agents;

    public AgentService(String filePath) {
        this.agentStorage = new JsonStorage<>(filePath, new TypeReference<List<DeliveryAgent>>() {});
        this.agents = agentStorage.loadFromJson();
    }

    public void saveAgent(DeliveryAgent agent) {
        agents.add(agent);
        agentStorage.saveToJson(agents);
    }

    public DeliveryAgent getAgentById(String agentId) {
        return agents.stream()
                .filter(agent -> agent.getUserId().equals(agentId))
                .findFirst()
                .orElse(null);
    }

    public List<DeliveryAgent> getAllAgents() {
        return agents;
    }

    public void updateAgentRating(String agentId, double newRating) {
        for (DeliveryAgent agent : agents) {
            if (agent.getUserId().equals(agentId)) {
                agent.addRating(newRating);
                break;
            }
        }
        agentStorage.saveToJson(agents);
    }
}

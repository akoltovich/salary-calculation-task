package by.akoltovich.salarycalculationtask.service;

import by.akoltovich.salarycalculationtask.entity.Team;
import by.akoltovich.salarycalculationtask.exception.TeamNotFoundException;
import by.akoltovich.salarycalculationtask.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getTeamById(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new TeamNotFoundException(teamId));
    }

    public void addTeam(Team team) {
        teamRepository.save(team);
    }

    public void updateTeam(Team team, Long id) {
        if (teamRepository.existsById(id)) {
            teamRepository.save(Team.builder()
                    .teamId(id)
                    .totalAmount(team.getTotalAmount())
                    .build());
        } else {
            throw new TeamNotFoundException(id);
        }
    }

    public void deleteTeam(Long teamId) {
        if (!teamRepository.existsById(teamId)) {
            throw new TeamNotFoundException(teamId);
        } else {
            teamRepository.deleteById(teamId);
        }
    }

    public Team getIfExists(Long teamId) {
        return teamRepository.findById(teamId).orElseThrow(() -> new TeamNotFoundException(teamId));
    }
}

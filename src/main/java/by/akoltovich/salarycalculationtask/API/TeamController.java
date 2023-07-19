package by.akoltovich.salarycalculationtask.API;

import by.akoltovich.salarycalculationtask.entity.Team;
import by.akoltovich.salarycalculationtask.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
@Validated
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping(path = "/id")
    public Team getTeamById(@Positive @RequestParam Long teamId) {
        return teamService.getTeamById(teamId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTeam(@RequestBody @Valid Team team) {
        teamService.addTeam(team);
    }

    @PutMapping(path = "/{teamId}")
    public void updateTeam(@Valid @RequestBody Team team, @PathVariable Long teamId) {
        teamService.updateTeam(team, teamId);
    }

    @DeleteMapping(path = "/{teamId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeam(@Positive @PathVariable Long teamId) {
        teamService.deleteTeam(teamId);
    }
}

package cat.tecnocampus.fgcstations.application;


import cat.tecnocampus.fgcstations.application.DTOs.FriendUserDTO;
import cat.tecnocampus.fgcstations.application.DTOs.UserFriendsDTO;
import cat.tecnocampus.fgcstations.application.DTOs.UserTopFriend;
import cat.tecnocampus.fgcstations.application.mapper.MapperHelper;
import cat.tecnocampus.fgcstations.domain.Friend;
import cat.tecnocampus.fgcstations.domain.User;
import cat.tecnocampus.fgcstations.persistence.FriendRepository;
import cat.tecnocampus.fgcstations.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FgcFriendService {
    private final FriendRepository friendRepository;
    private final FcgUserService fcgUserService;
    private final UserRepository userRepository;

    public FgcFriendService(FriendRepository friendRepository, FcgUserService fcgUserService, UserRepository userRepository) {
        this.friendRepository = friendRepository;
        this.fcgUserService = fcgUserService;
        this.userRepository = userRepository;
    }

    public UserFriendsDTO getUserFriends(String username) {
        User user = fcgUserService.getDomainUser(username);

        // TODO 20: find all the friends of a user given her username. You can solve this exercise without any sql query
        //List<Friend> friends = new ArrayList<>(); //feed the list with the friends of the user
        List <Friend> allFriends = friendRepository.findAll();
        List <String> friendsOfUser = allFriends.stream()
                .filter(friend -> friend.getUsername().equalsIgnoreCase(username))
                .map (Friend ::getFriend)
                .toList();

        UserFriendsDTO friendsDTO = new UserFriendsDTO();
        friendsDTO.setUsername(username);
        friendsDTO.setFriends(friendsOfUser);
        return friendsDTO;

        //return MapperHelper.listOfAUserFriendsToUserFriendsDTO(friends);
    }

    public List<UserFriendsDTO> getAllUserFriends() {
        // TODO 21: find all the friends (domain) of all users. You can solve this exercise without leaving this file
        //  note that domain objects are mapped to DTOs
        List<User> allUsers = userRepository.findAll();
        List<Friend> allFriends = friendRepository.findAll();

        Map<String, List<String>> friendsByUser = allFriends.stream()
                .collect(Collectors.groupingBy(
                        Friend::getUsername, // Agrupar por username del usuario
                        Collectors.mapping(Friend::getFriend, Collectors.toList()) // Mapear solo los nombres de amigos
                ));

        return allUsers.stream()
                .map(user -> {
                    UserFriendsDTO dto = new UserFriendsDTO();
                    dto.setUsername(user.getUsername());
                    dto.setFriends(friendsByUser.getOrDefault(user.getUsername(), new ArrayList<>()));
                    return dto;
                })
                .toList();

        //return MapperHelper.allUserFriendListToListUserFriendsDTO(new ArrayList<>()); // replace the empty list with the list of all users
    }

    public void saveFriends(UserFriendsDTO userFriendsDTO) {
        User user = fcgUserService.getDomainUser(userFriendsDTO.getUsername());
        friendRepository.saveAll(MapperHelper.friendsDTOToUserListOfFriends(user, userFriendsDTO));
    }

    public List<UserTopFriend> getTop3UsersWithMostFriends() {
        // TODO 22: find the top 3 users with the most friends.
        return friendRepository.findTop3UsersWithMostFriends().stream().limit(3).toList();
    }

    // Find all users whose friends have a certain name
    public List<FriendUserDTO> getUsersByFriend(String friendName) {
        // TODO 23: find all users whose friends have a certain name.
        return friendRepository.getFriendbyName(friendName);
    }

}

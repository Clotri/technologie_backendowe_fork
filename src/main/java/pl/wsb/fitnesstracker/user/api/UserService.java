package pl.wsb.fitnesstracker.user.api;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param user The user to be created
     * @return The created user
     */
    User createUser(User user);

    /**
     * Updates an existing user with new data.
     *
     * @param userId id of the user to update
     * @param user   object containing new field values
     * @return the updated user
     */
    User updateUser(Long userId, User user);

    /**
     * Deletes a user by their ID.
     *
     * @param userId id of the user to delete
     */
    void deleteUser(Long userId);

}

package server;

public class DBAuthService implements AuthService {
    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        return DBHandler.getNickNameByLoginAndPassword(login, password);
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        return DBHandler.registration(login,password,nickname);
    }

    @Override
    public boolean changeNick(String oldNickname, String newNickname) {
        return DBHandler.changeNick(oldNickname, newNickname);
    }
}

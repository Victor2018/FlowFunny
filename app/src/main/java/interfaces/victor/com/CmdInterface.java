package interfaces.victor.com;

/**
 * Created by victor on 2016/3/29 0029.
 */
public interface CmdInterface {
    /**
     * 执行命令
     */
    void execute ();

    /**
     * 撤销命令
     */
    void unDo();
}

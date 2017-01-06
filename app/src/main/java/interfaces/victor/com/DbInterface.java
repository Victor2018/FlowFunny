package interfaces.victor.com;

import java.util.List;

import data.victor.com.Move;

/**
 * Created by victor on 2015/12/25.
 */
public interface DbInterface {
    void insert(Object data);
    void delete(int categoryType, int actionType);
    void update(Move channel);
    List<Move> query(int categoryType, int actionType);
}

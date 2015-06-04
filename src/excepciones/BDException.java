/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package excepciones;

import java.sql.SQLException;

/**
 *
 * @author juacelo
 */
public class BDException extends SQLException{
      public BDException(String msg) {
        super(msg);
    }
}

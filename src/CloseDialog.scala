import javax.swing._


/**
  * Created by pc on 09/06/2017.
  */
class CloseDialog {
  private var title = ""
  private var message = ""
  private var mode = false

  def this(msg: String, ttl: String, m: Boolean) {
    this()
    message = msg
    title = ttl
    mode = m
    if (m) warning
    else if (!m) show
  }

  def this(msg: String, ttl: String) {
    this()
    message = msg
    title = ttl
  }

  def show: Int = {
    val result = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION)
    result
  }

  def warning: Int = {
    val result = JOptionPane.showConfirmDialog(MainScreen.getTabPane, message, title, JOptionPane.DEFAULT_OPTION)
    result
  }
}

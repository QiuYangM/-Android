package android.wan.com.wanandroid.custom

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.wan.com.wanandroid.R

/**
 * @type  $method$
 * @explain $EXPLAIN$
 * @author owner$.
 * @creat time 2019/6/3$ 11:12$.
 */
 class ProGressDialog : AlertDialog {

    constructor(context: Context) : super(context)
    constructor(context: Context?, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?) : super(context, cancelable, cancelListener)
    constructor(context: Context?, themeResId: Int) : super(context, themeResId)

   init {
       setContentView(R.layout.dialog_progress)
       setCanceledOnTouchOutside(true)
   }
}
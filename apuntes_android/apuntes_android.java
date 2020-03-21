================================================================================
===============								Manejando cambios 								================
================================================================================
• Sin manejador para cada botón
• Usamos uno para cambios en el grupo
________________________________________________________________________________
________________________________________________________________________________
Varios botones tienen el mismo identificador, de manera que me da igual en que grupo este, puedo usar el mismo manejador de eventos.

<RadioGroup
 android:id="@+id/grp1"
 ...
 <RadioButton
 android:id="@+id/grphoriz"												// • cuando pongo @+ estoy declarando un identificado
 android:layout_width="wrap_content"
 android:layout_height="wrap_content"
 android:checked="true"
 android:text="@string/horiz" />
 ...
 </RadioGroup>

 <RadioGroup
 android:id="@+id/grp2"
 ...
 <RadioButton
 android:id="@id/grphoriz"												// • cuando pongo @ lo estoy usando
 android:layout_width="wrap_content"
 android:layout_height="wrap_content"
 android:checked="true"
 android:text="@string/horiz" />
 ...
 </RadioGroup>
________________________________________________________________________________
________________________________________________________________________________
registo un manejador para el grupo, luego tengo que barrerme los botones buscando si hay alguno apretado o no.
o puedo usar el view que me pasan.

 public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// he registrado un manejador para el grupo en lugar de para el boton
		RadioGroup g1 = (RadioGroup)findViewById(R.id.grp1);
		RadioGroup g2 = (RadioGroup)findViewById(R.id.grp2);

		Orientador or = new Orientador(this);
		//lo que recibo es el grupo no es el view
		g1.setOnCheckedChangeListener(or);
		g2.setOnCheckedChangeListener(or);
}

________________________________________________________________________________
________________________________________________________________________________
Aqui puedo mirar los identificadoes, y como he usado un identificador para vertical y otro para horizontal en los dos grupos puedo usar el mismo manejador  para los dos.

class Orientador implements OnCheckedChangeListener {
	Activity mainWin;
	Orientador(Activity w){
		mainWin = w;
	}
	@Override
	public void onCheckedChanged(RadioGroup g, int id) {
		int or = LinearLayout.HORIZONTAL;
		if(id == R.id.grpvert){
			or = LinearLayout.VERTICAL;
		}
		g.setOrientation(or);
	}
}

================================================================================
===============										CheckButtons									================
================================================================================

• Checked/Unchecked
• Podemos marcar varios a la vez
________________________________________________________________________________
________________________________________________________________________________
Como he usado un identificador para vertical y otro para horizontal en los dos grupos, puedo usar el mismo manejador para los dos.

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
		...
		android:orientation="vertical"
		tools:context=".MainActivity" >

	<LinearLayout
		android:layout_height="wrap_content"
		android:layout_width="wrap_content"
		android:orientation="horizontal">
		<CheckBox
			android:layout_width="wrap_content"
			android:layout_height="wrap_content"
			android:checked="true"
			android:onClick="check"
			android:text="Turbo" />
		<CheckBox
			...
			android:text="Boost" />
	</LinearLayout>

	<LinearLayout
		...
	</LinearLayout>

</LinearLayout>

________________________________________________________________________________
________________________________________________________________________________

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	...
	public void check(View v){
		CheckBox b = (CheckBox) v;
		String txt = b.getText() + " ";
		if(b.isChecked()){
		txt += "on";
		}else{
		txt += "off";
		}
		int time = Toast.LENGTH_SHORT;
		Toast msg = Toast.makeText(MainActivity.this, txt, time);
		msg.show();
	}
}
________________________________________________________________________________
________________________________________________________________________________

¿manejar o no?

• a veces no querremos un manejador.
• Consultamos si el botón está “checked” cuando lo necesitemos y listo.

================================================================================
===============							Imágenes para botones								================
================================================================================
son botones que pueden cambiar la imagen dependiendo del estado en el que esté.
en Res  en drawable

<ImageButton
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	android:src="@drawable/mi_img_boton"/>
	// Y como imagen podemos usar un selector
	// en lugar de un .png (en el directorio drawable, mi_img_boton.xml)

	<?xml version="1.0" encoding="utf-8"?>
	<selector xmlns:android="http://schemas.android.com/apk/res/android">
	// aqui tenemos las dos imagenes

		<item android:state_pressed="true" android:drawable="@drawable/pulsado" />
		<item android:state_focused="true" android:drawable="@drawable/foco_en" />
		<item android:drawable="@drawable/normal" />
</selector>

================================================================================
===============											LISTAS											================
================================================================================
• Para seleccionar algún elemento de entre un conjunto de ellos.
• Normalmente “pull down”
• Precauciones:
• La etiqueta se muestra según esté plegada/desplegada
• Poner un TextView antes que ocupe el lugar de la etiqueta
• Se envía un evento extra al principio y es mejor ignorarlo
________________________________________________________________________________
________________________________________________________________________________
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
	xmlns:tools="http://schemas.android.com/tools"
	android:layout_width="match_parent"
	android:layout_height="match_parent"
	android:orientation="vertical"
	tools:context=".MainActivity" >

	<TextView
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
		android:text="@string/etiqueta"/>
		// la lista, cuando este oculta no va a ocupar nada.

	<Spinner
		android:id="@+id/lista"
		android:prompt="@string/etiqueta"
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
		android:entries="@array/entradas"
	/>
</LinearLayout>
________________________________________________________________________________
________________________________________________________________________________

<?xml version="1.0" encoding="utf-8"?>
<resources>
	<string name="app_name">App1</string>
	<string name="hello_world">Hello world!</string>
	<string name="menu_settings">Settings</string>
	<string name="etiqueta">Ejemplo de Lista</string>
	// string array con las entradas
	<string-array name="entradas">
		<item>Hola</item>
		<item>Caracola</item>
		<item>Adios</item>
		<item>Oops</item>
	</string-array>
</resources>

________________________________________________________________________________
________________________________________________________________________________

public class MainActivity extends Activity {
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Spinner lista = (Spinner)findViewById(R.id.lista);
		// le añado un manejador
		lista.setOnItemSelectedListener(new Seleccion(this));
	}
________________________________________________________________________________
________________________________________________________________________________
private class Seleccion implements OnItemSelectedListener {
	private MainActivity main;
	private boolean isFirst;
	public Seleccion(MainActivity m){
		main = m;
		isFirst = true;
	}
	@Override
	// son genericos en java, tipo de datos parametricos
	// indice para recuperarlo

	public void onItemSelected(AdapterView<?> lista, View item, int idx,long id) {
		if(isFirst){
			isFirst = false;
			return;
		}
		String str = lista.getItemAtPosition(idx).toString(); // lista que es el tipo de dartos que, le pides los datos seleccionados y se convierte a un string.

		int time = Toast.LENGTH_SHORT;
		str += " seleccionado";
		Toast msg = Toast.makeText(MainActivity.this, str, time);
		msg.show();
}

________________________________________________________________________________
________________________________________________________________________________
private class Seleccion implements OnItemSelectedListener {
...
@Override
// extiende la lista y la encoges pero no has seleccionado entradas
public void onNothingSelected(AdapterView<?> lista) {
	int time = Toast.LENGTH_SHORT;
	String str = "nada seleccionado";
	Toast msg = Toast.makeText(MainActivity.this, str, time);
	msg.show();
	}
}
================================================================================
===============								Listas dinámicas									================
================================================================================
// crear parte del interfaz de usario desde java

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
	xmlns:tools="http://schemas.android.com/tools"
	android:layout_width="match_parent"
	android:layout_height="match_parent"
	android:orientation="vertical"
	tools:context=".MainActivity" >

	<TextView
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
		android:text="@string/etiqueta"/>
	<Spinner
		android:id="@+id/lista"
		android:prompt="@string/etiqueta"
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
	/>
</LinearLayout>
________________________________________________________________________________
________________________________________________________________________________

Ahora no tenemos ningún array de strings, sino que las voy a dar de alta luego

<?xml version="1.0" encoding="utf-8"?>
<resources>
	<string name="app_name">App1</string>
	<string name="hello_world">Hello world!</string>
	<string name="menu_settings">Settings</string>
	<string name="etiqueta">Ejemplo de Lista</string>
</resources>
________________________________________________________________________________
________________________________________________________________________________
public class MainActivity extends Activity {
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	Spinner lista = (Spinner)findViewById(R.id.lista);
	// array de strings
	String[] nombres = {"Hola", "Caracola", "Adios", "Oops"};
	// adaptador al que legal
	// el que recibe

	ArrayAdapter<String> arry =
		new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombres);
	arry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	// elementos para un spinner .simple_spinner_dropdown_item
	lista.setAdapter(arry);
	lista.setOnItemSelectedListener(new Seleccion(this));
}
________________________________________________________________________________
________________________________________________________________________________

public class MainActivity extends Activity {
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Spinner lista = (Spinner)findViewById(R.id.lista);
		String[] nombres = {"Hola", "Caracola", "Adios", "Oops"};
		ArrayAdapter<String> arry = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, nombres);
		arry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		lista.setAdapter(arry);
		lista.setOnItemSelectedListener(new Seleccion(this));
	}
}

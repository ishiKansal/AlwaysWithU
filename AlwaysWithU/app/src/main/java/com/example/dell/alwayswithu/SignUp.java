package com.example.dell.alwayswithu;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class SignUp extends AbsRuntimePermission implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private EditText etdob;
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog fromDatePickerDialog;
    EditText edtName, edtEmail, edtPhone, edtDOB, edtPassword, edtPassword1;
    private static final int REQUEST_PERMISSION = 10;
    public static final String TAG = "Test";

    Spinner spin1, spin;
    MyDatabase mydb;
    SQLiteDatabase db;
    SmsManager s = SmsManager.getDefault();

    String[] stateNames = {" -- Select State -- ", "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jammu and Kashmir", "Jharkhand", "Karnataka", "Kerala", "MadhyaPradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal"};
    String[] cityNames={"-- Select City --"};
    String[] Assam = {"Guwahati [Gauhati]", "Silchar", "Dibrugarh", "Jorhat", "Nagaon (Nowgong)", "Tinsukia", "Tezpur", "Bongaigaon", "Karimganj", "Dhubri", "Diphu", "North Lakhimpur", "Lumding", "Goalpara", "Sibsagar", "Haflong", "Barpeta", "Golaghat", "Bilasipara", "Lanka", "Hojai", "Barpeta Road", "Digboi", "Kokrajhar", "Hailakandi"};
    String[] Arunachal = {"Aalo(Along)", "Anini", "Basar", "Boleng", "Bomdila", "Changlang", "Daporijo", "Deomali", "Dirang", "Hawai", "Itanagar", "Jairampur", "Khonsa", "Koloriang", "Longding", "Miao", "Naharlagun", "Namsai", "Pasighat", "Roing", "Rupa", "Sagalee", "Seppa", "Tawang", "Tezu", "Yingkiong", "Ziro"};
    String[] Andhra = {"Visakhapatnam", "Vijayawada", "Guntur", "Nellore", "Kurnool", "Rajamundry", "Tirupati", "Kakinada", "Kadapa", "Anantapur", "Vizianagaram", "Eluru", "Ongole", "Nandyal", "Machilipatnam", "Adoni", "Tenali", "Proddatur", "Chittoor", "Hindupur", "Bhimavaram", "Madanapalle", "Guntakal", "Srikakulam", "Palakollu", "Dharmavaram", "Gudivada", "Narasaraopet", "Tadipatri", "Tadepalligudem", "Chilakaluripet", "Amaravati"};
    String[] Bihar = {"Patna", "Gaya", "Bhagalpur", "Muzaffarpur", "Purnia", "Darbhanga", "Bihar Sharif", "Arrah", "Begusarai", "Katihar", "Munger", "Chhapra", "Danapur", "Bettiah", "Saharsa", "Sasaram", "Hajipur", "Dehri", "Siwan", "Motihari", "Nawada", "Bagaha", "Buxar", "Kishanganj", "Sitamarhi", "Jamalpur", "Jehanabad", "Aurangabad"};
    String[] Goa = {"Bicholim", "Canacona", "Cuncolim", "Curchorem", "Mapusa", "Margao", "Mormugao", "Panaji", "Pernem", "Ponda", "Quepem", "Sanguem", "Sanquelim", "Valpoi"};
    String[] TamilNadu = {"Chennai", "Coimbatore", "Madurai", "Tiruchirappalli", "Tiruppur", "Salem", "Erode", "Vellore", "Tirunelveli", "Thoothukkudi", "Nagercoil", "Thanjavur", "Dindigul", "Cuddalore", "Kanchipuram", "Tiruvannamalai", "Kumbakonam", "Rajapalayam", "Pudukottai", "Hosur", "Ambur", "Karaikkudi", "Neyveli", "Nagapattinam"};
    String[] Tripura = {"Agartala", "Amarpur", "Belonia", "Dharmanagar", "Kailasahar", "Kamalpur", "Khowai", "Kumarghat", "Ranirbazar", "Sabroom", "Sonamura", "Teliamura", "Udaipur", "Bishalgarh", "Santirbazar", "Ambassa", "Jirania", "Mohanpur", "Melaghar", "Panisagar"};
    String[] UP = {"Kanpur", "Lucknow", "Ghaziabad", "Agra", "Varanasi", "Meerut", "Allahabad", "Bareilly", "Aligarh", "Moradabad", "Saharanpur", "Gorakhpur", "Faizabad", "Firozabad", "Jhansi", "Muzaffarnagar", "Mathura", "Budaun", "Rampur", "Shahjahanpur", "Farrukhabad-cum-Fategarh", "Maunath Bhanjan", "Hapur", "Noida", "Etawah", "Mirzapur-cum-Vindhyachal", "Bulandshahr", "Sambhal", "Amroha", "Hardoi", "Fatehpur", "Raebareli", "Orai", "Sitapur", "Bahraich", "Modinagar", "Unnao", "Jaunpur", "Lakhimpur", "Hathras", "Banda", "Pilibhit", "Mughalsarai", "Barabanki", "Khurja", "Gonda", "Mainpuri", "Lalitpur", "Etah", "Deoria", "Ujhani", "Ghazipur", "Sultanpur", "Azamgarh", "Bijnor", "Sahaswan", "Basti", "Chandausi", "Akbarpur", "Ballia", "Tanda", "Greater Noida", "Shikohabad", "Shamli", "Khair", "Kasganj"};
    String[] Uttrakhand = {"Dehradun", "Haridwar", "Roorkee", "Haldwani-cum-Kathgodam", "Rudrapur", "Kashipur", "Rishikesh"};
    String[] West_Bengal = {"Kolkata", "Asansol", "Siliguri", "Durgapur", "Bardhaman", "Malda", "Baharampur", "Habra", "Kharagpur", "Shantipur", "Dankuni", "Dhulian", "Ranaghat", "Haldia", "Raiganj", "Krishnanagar", "Nabadwip", "Medinipur", "Jalpaiguri", "Balurghat", "Basirhat", "Bankura", "Chakdaha", "Darjeeling", "Alipurduar", "Purulia", "Jangipur", "Bangaon", "Cooch Behar"};
    String[] Sikkim = {"Gangtok", "Gyalshing", "Jorethang", "Mangan", "Namchi", "Nayabazar", "Rangpo", "Rhenak", "Singtam"};
    String[] Rajasthan = {"Jaipur", "Jodhpur", "Kota", "Bikaner", "Ajmer", "Udaipur", "Bhilwara", "Alwar", "Bharatpur", "Sikar", "Sri Ganganagar", "Pali", "chittorgarh", "Tonk", "Kishangarh", "Beawar", "Hanumangarh", "dholpur", "Gangapur city", "Sawai Madhopur", "churu", "Jhunjhunu"};
    String[] Punjab = {"Amritsar", "Barnala", "Bathinda", "Faridkot", "Fatehgarh Sahib", "Fazilika", "Firozpur", "Gurdaspur", "Hoshiarpur", "Jalandhar", "Kapurthala", "Ludhiana", "Malerkotla", "Mansa", "Moga", "Ajitgarh (Mohali)", "Muktsar Sahib", "Patiala", "Pathankot", "Rupnagar", "Sangrur", "Shahid Bhagat Singh Nagar", "Tarn Taran"};
    String[] Manipur = {"Aihol", "Angem", "Asukukhwami", "Atengba", "Berabak", "Bishenpur", "Bishnupur", "Boaroami", "Boljang", "Bongba Khulen", "Bongbal Khulen", "Buri Bazar", "Chahong Khuno", "Chakpi Karong", "Chalong", "Chammu", "Changmun", "Charoi", "Chakoklong", "Chassud", "Chaton", "Chattrik", "Chingjui", "Churachandpur", "Dzulake", "Gaziphema", "Gouthang", "Hanggou", "Haochong", "Hengken", "Hengtam", "Huggiyam", "Humine", "Humpum", "Imphal", "Jessami", "Joipi", "Jun", "Kadi", "Kaiphundai", "Kakching", "Kakching Khunou", "Kalapahar", "Kampang", "Kampha", "Kang-gum", "Kangchup Khul", "Kanglatongbi", "Kangpat", "Kangpokpi", "Kanjang", "Karong", "Kasom Khullen", "Katang", "Kekrima", "Khamson", "Kharasom", "Khebuching", "Khelma", "Khengoi", "Khoinoi", "Khubung Khulen", "Khunthak", "Kidzematuma", "Kivikhu", "Koiri", "Kumbi", "Kuvukhu", "Lagairong", "Lakhan Khuman", "Lalongmi", "Lalpani", "Lamdanmai", "Langga", "Langkhong", "Laphurak", "Leishan", "Long", "Longbi Hirei", "Longpi", "Lozaphohemi", "Lukhambi", "Lunghan", "Lungithang", "Maibi Khunou", "Makeng", "Manipur Road", "Mao Songsang", "Maohing", "Marabume", "Maram", "Mawhin", "Mayang Imphal", "Meiring", "Meiti", "Metikumi", "Moirang", "Molnom", "Molvailup"};
    String[] Meghalaya = {"Cherrapunji", "Mairang", "Mankachar", "Nongpoh", "Nongstoin", "Shillong", "Tura"};
    String[] Mizoram = {"Aizawl", "Darlawn", "Khawhai", "Kolasib", "Lunglei", "Mamit", "North Vanlaiphai", "Saiha", "Sairang", "Saitlaw", "Serchhip", "Thenzawl"};
    String[] Nagaland = {"Dimapur", "Kohima", "Mokokchung", "Tuensang", "Wokha", "Zunheboto"};
    String[] Odisha = {"Bhubaneswar", "Cuttack", "Raurkela", "Brahmapur", "Sambalpur", "Puri", "Baleshwar", "Bhadrak", "Baripada", "Balangir", "Jharsuguda", "Jaypur", "Bargarh", "Brajarajnagar", "Rayagada", "Bhawanipatna", "Paradip", "Dhenkanal", "Barbil", "Jatani", "Kendujhar", "Byasanagar", "Rajagangapur", "Sunabeda", "Koraput"};
    String[] Harayana = {"Faridabad", "Gurugram", "Panipat", "Ambala", "Yamunanagar", "Rohtak", "Hisar", "Karnal", "Sonipat", "Panchkula", "Bhiwani", "Sirsa", "Bahadurgarh", "Jind", "Thanesar", "Kaithal", "Rewari", "Palwal"};
    String[] HP = {"Shimla", "Solan", "Dharmsala", "Baddi", "Nahan", "Mandi", "Paonta Sahib", "Sundarnagar", "Chamba", "Una", "Kullu", "Hamirpur", "Bilaspur", "Yol Cantonment", "Nalagarh", "Nurpur", "Kangra", "Santokhgarh", "Mehatpur Basdehra", "Shamshi", "Parwanoo", "Manali", "Tira Sujanpur", "Ghumarwin", "Dalhousie", "Rohru", "Nagrota Bagwan", "Rampur", "Jawalamukhi", "Jogindernagar", "Dera Gopipur", "Sarkaghat", "Jhakhri", "Indora", "Bhuntar", "Nadaun", "Theog", "Kasauli Cantonment", "Gagret", "Chuari Khas", "Daulatpur", "Sabathu Cantonment", "Dalhousie Cantonment", "Palampur", "Rajgarh", "Arki", "Dagshai Cantonment", "Seoni", "Talai", "Jutogh Cantonment", "Chaupal", "Rewalsar", "Bakloh Cantonment", "Jubbal", "Bhota", "Banjar", "Naina Devi", "Kotkhai", "Narkanda"};
    String[] JK = {"Srinagar", "Jammu", "Anantnag", "Udhampur", "Baramula", "Sopore", "Kathua", "Bandipura", "Leh", "Rajauri", "Ganderbal", "Punch", "Kulgam", "Duru Verinag", "Bijbiara", "Kupwara", "Doda", "Akhnoor"};
    String[] Jharkhand = {"Jamshedpur", "Dhanbad", "Ranchi", "Bokaro Steel City", "Deoghar", "Phusro", "Hazaribagh", "Giridih", "Ramgarh", "Medininagar", "Chirkunda"};
    String[] Karnataka = {"Bengaluru", "Mysore", "Hubli-Dharwar", "Mangalore", "Belgaum", "Gulbarga", "Davanagere", "Bellary", "Bijapur", "Shimoga", "Tumkur", "Raichur", "Bidar", "Hospet", "Hassan", "Gadag-Betigeri", "Udupi", "Robertson Pet", "Bhadravati", "Chitradurga", "Kolar", "Mandya", "Chikmagalur", "Gangawati", "Bagalkot"};
    String[] Kerala = {"Trivandrum", "Cochin", "Calicut", "Trichur", "Quilon", "Alappuzha", "Kottayam", "Palakkad", "Manjeri", "Malappuram", "Tellicherry", "Ponnani", "Vatakara", "Kanhangad", "Taliparamba", "Payyanur", "Koyilandy", "Neyyattinkara", "Kalamassery", "Anchal"};
    String[] MP = {"Indore", "Bhopal", "Jabalpur", "Gwalior", "Ujjain", "Sagar", "Dewas", "Satna", "Ratlam", "Rewa", "Murwara", "Singrauli", "Burhanpur", "Khandwa", "Bhind", "Chhindwara", "Guna", "Shivpuri", "Vidisha", "Chhatarpur", "Damoh", "Mandsaur", "Khargone", "Neemuch", "Pithampur", "Hoshangabad", "Itarsi", "Sehore", "Betul", "Seoni", "Datia", "Nagda"};
    String[] Maharashtra = {"Mumbai", "Nagpur", "Pune", "Nashik", "Vasai-Virar", "Aurangabad", "Solapur", "Bhiwandi", "Amravati", "Malegaon", "Kolhapur", "Nanded", "Sangli", "Jalgaon", "Akola", "Latur", "Ahmadnagar", "Dhule", "Ichalkaranji", "Chandrapur", "Parbhani", "Jalna", "Bhusawal", "Navi Mumbai", "Panvel"};
    String[] Chattisgarh = {"Raipur", "Bhilai", "Bilaspur", "Korba", "Raj Nandgaon", "Raigarh", "Jagdalpur", "Ambikapur", "Dhamtari", "Chirmiri", "Bhatapara", "Mahasamund", "Dalli-Rajhara", "Kawardha", "Champa", "Naila Janjgir", "Kanker", "Dongragarh", "Tilda Neora", "Mungeli", "Manendragarh", "Kondagaon", "Gobranawapara", "Bemetara", "Baikunthpur"};
    String[] Gujarat={"Ahmedabad","Surat","Vadodara","Rajkot","Bhavnagar","Jamnagar","Junagadh","Anand","Navsari","Surendranagar","Morbi"};


    ArrayAdapter adapter, adapterCity, adapter1, adapter2, adapter3, adapter4, adapter5, adapter6, adapter7, adapter8, adapter9, adapter10, adapter11, adapter12, adapter13, adapter14, adapter15, adapter16, adapter17, adapter18, adapter19, adapter20, adapter21, adapter22, adapter23, adapter24, adapter25, adapter26, adapter27, adapter28;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        edtName= (EditText) findViewById(R.id.edtName);
        edtEmail= (EditText) findViewById(R.id.edtEmail);
        edtPhone= (EditText) findViewById(R.id.edtPhone);
        edtPassword= (EditText) findViewById(R.id.edtPassword);
        edtPassword1= (EditText) findViewById(R.id.edtPassword1);
        mydb=new MyDatabase(this);

        // Date Picker

        dateFormatter=new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        findViewById();
        setDateTimeField();


        // Spinner
        spin1 = (Spinner) findViewById(R.id.citySpinner);

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        spin = (Spinner) findViewById(R.id.stateSpinner);
        adapterCity=new ArrayAdapter(this,android.R.layout.simple_list_item_1, cityNames);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stateNames);
        adapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Andhra);
        adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Arunachal);
        adapter3 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Assam);
        adapter4 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Bihar);
        adapter5 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Chattisgarh);
        adapter6 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Goa);
        adapter7 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Gujarat);
        adapter8 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Harayana);
        adapter9 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, HP);
        adapter10 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, JK);
        adapter11 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Jharkhand);
        adapter12 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Karnataka);
        adapter13 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Kerala);
        adapter14 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, MP);
        adapter15 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Maharashtra);
        adapter16 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Manipur);
        adapter17 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Meghalaya);
        adapter18 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Mizoram);
        adapter19 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Nagaland);
        adapter20 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Odisha);
        adapter21 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Punjab);
        adapter22 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Rajasthan);
        adapter23 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Sikkim);
        adapter24 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, TamilNadu);
        adapter25 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Tripura);
        adapter26 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Uttrakhand);
        adapter27 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, UP);
        adapter28 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, West_Bengal);


        spin.setAdapter(adapter);
        spin1.setAdapter(adapterCity);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 1) {
                    spin1.setAdapter(adapter1);
                }
                else if(i==2){
                    spin1.setAdapter(adapter2);
                }
                else if(i==3){
                    spin1.setAdapter(adapter3);
                }
                else if(i==4){
                    spin1.setAdapter(adapter4);
                }
                else if(i==5){
                    spin1.setAdapter(adapter5);
                }
                else if(i==6){
                    spin1.setAdapter(adapter6);
                }
                else if(i==7){
                    spin1.setAdapter(adapter7);
                }
                else if(i==8){
                    spin1.setAdapter(adapter8);
                }
                else if(i==9){
                    spin1.setAdapter(adapter9);
                }
                else if(i==10){
                    spin1.setAdapter(adapter10);
                }
                else if(i==11){
                    spin1.setAdapter(adapter11);
                }
                else if(i==12){
                    spin1.setAdapter(adapter12);
                }
                else if(i==13){
                    spin1.setAdapter(adapter13);
                }
                else if(i==14){
                    spin1.setAdapter(adapter14);
                }
                else if(i==15){
                    spin1.setAdapter(adapter15);
                }
                else if(i==16){
                    spin1.setAdapter(adapter16);
                }
                else if(i==17){
                    spin1.setAdapter(adapter17);
                }
                else if(i==18){
                    spin1.setAdapter(adapter18);
                }
                else if(i==19){
                    spin1.setAdapter(adapter19);
                }
                else if(i==20){
                    spin1.setAdapter(adapter20);
                }
                else if(i==21){
                    spin1.setAdapter(adapter21);
                }
                else if(i==22){
                    spin1.setAdapter(adapter22);
                }
                else if(i==23){
                    spin1.setAdapter(adapter23);
                }
                else if(i==24){
                    spin1.setAdapter(adapter24);
                }
                else if(i==25){
                    spin1.setAdapter(adapter25);
                }
                else if(i==26){
                    spin1.setAdapter(adapter26);
                }
                else if(i==27){
                    spin1.setAdapter(adapter27);
                }
                else if(i==28){
                    spin1.setAdapter(adapter28);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void onPermissionGranted(int requestCode) {
        Log.d("Request Code ", "onPermissionGranted: " + requestCode);
        Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();

    }

    private void setDateTimeField() {
        etdob.setOnClickListener((View.OnClickListener) this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                etdob.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    private void findViewById() {
        etdob = (EditText) findViewById(R.id.etdob);
        etdob.setInputType(InputType.TYPE_NULL);
        etdob.requestFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public void onClick(View view) {

        fromDatePickerDialog.show();

    }

    public void submit(View view) {
        String a1, a2, a3, a4, a5, a6, a7, a8;
        a1 = edtName.getText().toString();
        a2 = edtEmail.getText().toString();
        a3 = edtPhone.getText().toString();
        a4 = etdob.getText().toString();
        a5 = edtPassword.getText().toString();
        a6 = edtPassword1.getText().toString();
        a7 = spin.getSelectedItem().toString();
        a8 = spin1.getSelectedItem().toString();


        if (TextUtils.isEmpty(a1)) {
            edtName.setError("Field cannnot be empty");
        } else if (TextUtils.isEmpty(a2)) {
            edtEmail.setError("Field cannot be empty");
        } else if (TextUtils.isEmpty(a3)) {
            edtPhone.setError("Field cannot be empty");
        } else if (TextUtils.isEmpty(a5)) {
            edtPassword.setError("Field cannot be empty");
        } else if (TextUtils.isEmpty(a6)) {
            edtPassword1.setError("Field cannot be empty");
        } else {

            if (a1.length() < 3) {
                edtName.setError("Atleast 3 character required");
            } else if (!a2.matches("[@]{1}") && a2.matches("[a-z]{1,50}")) {
                Toast.makeText(this, "hhh", Toast.LENGTH_SHORT).show();
                edtEmail.setError("Invalid Email");
            }
            /*else if (!(a2.endsWith("gmail.com"))|!(a2.endsWith("ymail.com"))|!(a2.endsWith("yahoo.com"))|!(a2.endsWith("rediff.com"))||!(a2.endsWith("hotmail.com"))){
                edtEmail.setError("Invalid Email");
            }*/
            else if (a3.length() != 10) {
                edtPhone.setError("Invalid Phone Number");
            } else if (!(a5.equals(a6))) {
                edtPassword.setError("Password dont match");
            } else {
                db = mydb.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(MyDatabase.NAME, a1);
                cv.put(MyDatabase.EMAIL, a2);
                cv.put(MyDatabase.PHONE, a3);
                cv.put(MyDatabase.DOB, a4);
                cv.put(MyDatabase.STATE, a6);
                cv.put(MyDatabase.CITY, a7);
                cv.put(MyDatabase.PASSWORD, a5);
                long id = db.insert(MyDatabase.TABLE_NAME, null, cv);
                Log.d(TAG, "id = " + id);
                if (id < 0) {
                    Toast.makeText(this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                } else {
                    //openSignUp();
                    Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();

                    Random r = new Random();

                    int r1 = r.nextInt(10000);
                    String msg = Integer.toString(r1);

                    s.sendTextMessage(a3, null, msg, null, null);

                    Intent i = new Intent(this, OTP.class);
                    i.putExtra("msg", msg);
                    startActivity(i);
                }
            }
        }


    }

    private void openSignUp() {
        requestAppPermission(new String[]{
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_CONTACTS,
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET},
                R.string.msg, REQUEST_PERMISSION);
        //Intent intent = new Intent(this, SignUp.class);
        //startActivity(intent);
    }
}

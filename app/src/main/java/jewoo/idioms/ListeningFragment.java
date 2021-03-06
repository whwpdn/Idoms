package jewoo.idioms;

//import android.support.v4.app.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.File;
import java.util.ArrayList;


/**
 * Created by jewoo on 2017. 2. 28..
 */
public class ListeningFragment extends Fragment implements ActivityCompat.OnRequestPermissionsResultCallback {


    private ListView mListeningList;
    Context c;
    View v ;
    private ArrayAdapter<String> mListAdapter;
    ArrayList<String> mArrFiles;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        v= inflater.inflate( R.layout.listening_fragment, container, false );

        mArrFiles = new ArrayList<String>();
        mListAdapter= new ArrayAdapter<String>(getActivity(), R.layout.list_group,R.id.tvparent,mArrFiles);

        mListeningList = (ListView)v.findViewById(R.id.listeningList);
        mListeningList.setAdapter(mListAdapter);



        mListeningList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // call video activity
                Intent i = new Intent(getActivity(),VideoActivity.class);
                String filename =  mListAdapter.getItem(position);
                String path = getActivity().getExternalFilesDir(null).getAbsolutePath()+"/samples/" + filename;
                i.putExtra("videofileuri",path);
                startActivity(i);
                //ChangeVideoFragment();
            }
        });

        initFileList();
        return v;

    }
    public void initFileList(){

        String path = getActivity().getExternalFilesDir(null).getAbsolutePath()+"/samples/";
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        if (files != null) {

            for (int i = 0; i < files.length; i++) {
                mArrFiles.add(files[i].getName());
            }
        }

    }

    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        super.onActivityCreated(savedInstanceState);
        //mGroupList = new ArrayList<String>();
        //here setting all the values to Parent and child classes


    }

}

public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

    // Mendapatkan referensi ke tombol Settings
    Button settingsButton = rootView.findViewById(R.id.buttonS);

    // Menambahkan onClickListener ke tombol Settings
    settingsButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Membuka SettingsActivity saat tombol "Settings" diklik
            Intent intent = new Intent(getActivity(), SettingsActivity.class);
            startActivity(intent);
        }
    });

    return rootView;
}
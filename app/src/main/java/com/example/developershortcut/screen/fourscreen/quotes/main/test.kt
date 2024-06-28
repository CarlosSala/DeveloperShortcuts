


  /*  // a delegate create one instance of viewModel because is a singleton
    private val viewModel: QuoteViewModel by viewModels()



    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    companion object {

        private const val ACCESS_COARSE_LOCATION =
            android.Manifest.permission.ACCESS_COARSE_LOCATION

        const val DEFAULT_REGION = "US"
    }

    // this launcher is used to request permissions if these are not granted
    // since permissionsStatus()
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->

            when {
                isGranted -> {
                    // permission granted, do something
                    requestPopularMovies()
                }

                shouldShowRequestPermissionRationale(ACCESS_COARSE_LOCATION) -> {
                    // permission denied, show explanation
                    Toast.makeText(
                        this,
                        "This permissions is necessary for this app",
                        Toast.LENGTH_LONG
                    ).show()
                }

                else -> {
                    // permission denied, show explanation
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show()
                }
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // for to get location

        permissionsStatus()

        initRecyclerView()

        viewModel.listPopularMovies.observe(this) {
            moviesAdapter.setListMovies(it)
            moviesAdapter.notifyDataSetChanged()
        }

        viewModel.progressVisible.observe(this) {
            binding.pbMovies.isVisible = it
        }

        // is implemented the interface
        *//*        object : MovieClickListener {
                override fun onMovieClicked(movie: com.example.jetpackcompose.ui.screenExamples.retrofit.domain.model.com.example.developershortcut.screen.fourscreen.quotes.model.Movie) {
                    Toast.makeText(
                        this@CourseActivity,
                        movie.title,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }*//*

    }

    private fun permissionsStatus() {

        // check if permission is already granted
        if (checkStatusPermission()) {
            // permission granted, do something
            requestPopularMovies()

        } else {
            // permission denied, launch permission request
            requestPermissionLauncher.launch(ACCESS_COARSE_LOCATION)
        }
    }




    private fun checkStatusPermission(): Boolean {

        // check if permission is already granted
        val checkPermissions = PermissionChecker.checkSelfPermission(
            this,
            ACCESS_COARSE_LOCATION
        ) == PermissionChecker.PERMISSION_GRANTED

        return checkPermissions
    }

    @SuppressLint("MissingPermission")
    private fun requestPopularMovies() {

        // request lastLocation to GPS
        fusedLocationProviderClient.lastLocation.addOnCompleteListener {

            if (it.result != null) {

                viewModel.requestPopularMovies(getRegionFromLocation(it.result))

            } else {
                viewModel.requestPopularMovies(DEFAULT_REGION)
            }
        }
    }

    private fun getRegionFromLocation(location: Location): String {

        val geocoder = Geocoder(this)
        val result = geocoder.getFromLocation(location.latitude, location.longitude, 1)

        return result?.firstOrNull()?.countryCode ?: DEFAULT_REGION
    }*/

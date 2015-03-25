
TK 03/25/2015
Extracting Cities from Address String
    > a bit more difficult:
    1 meadow ave east meadow ny 11554 -> is it East Meadow or Meadow ny?
    find_place algorithm
            By here we either do or do not have a zip code
            Case 1: Zip code is matched in DB with city name
                1a. IF city name matches [fuzzy or strict] with place: THEN extract city
                1b. IF city name != match: THEN ??
            Case 2: Zip Code is not matched to DB

            Case 1b and 2 end up in the same scenario:
                'Guess City' -> can do this based on extracting
                    - last 3 of words in address string
                    - last 2 of words in address string
                    - last 1 of words in address string
                Try to match that against DB
What we Know...
    1. there are 43k zip/city combos in the GeoNames File
    2. City word lengths [Length; Count]:
        1;33058
        4;41
        5;8
        3;656
        2;9866
        6;2
    so, we could make an initial pass at it and extract the last 1 or 2 words -> pending that we have
        extracted a city/zip - or if they even existed.
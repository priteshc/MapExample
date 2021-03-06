package com.example.atishay.myexample;

/**
 * Created by Atishay on 06-02-2018.
 */

public class Constant {


    public static final String MESSAGE = "<PidData>\n" +
            "<Resp errCode=\"0\" errInfo=\"SUCCESS\" fCount=\"1\" nmPoints=\"36\" qScore=\"79\" />\n" +
            "<DeviceInfo dpId=\"SECUGEN.SGI\" rdsId=\"SGI.WIN.001\" rdsVer=\"1.0.0\" dc=\"34fa8717-0c4c-4332-a69c-781e496ae1a2\" mi=\"HU20\" mc=\"MIIDwDCCAqigAwIBAgIEAQWajzANBgkqhkiG9w0BAQUFADCB3jEsMCoGA1UEAxMjRFMgU0VDVUdFTiBJTkRJQSBQUklWQVRFIExJTUlURUQgMDExNTAzBgNVBDMTLFVOSVQgTk8gNjA1LTYwOCA2VEggRkxPT1IgIEMgIFdJTkcgU09MQVJJUyAxMSwwKgYDVQQJEyNTQUtJIFZJSEFSIFJPQUQgQU5ESEVSSSBFQVNUIE1VTUJBSTEUMBIGA1UECBMLTWFoYXJhc2h0cmExJjAkBgNVBAoTHVNFQ1VHRU4gSU5ESUEgUFJJVkFURSBMSU1JVEVEMQswCQYDVQQGEwJJTjAeFw0xODAxMTIwNDQ1MTZaFw0xODAyMTEwNDQ1MTZaMGUxGTAXBgNVBAMTEHNnaXJkc3N1YnNjcmliZXIxDTALBgNVBAsTBEFVVEgxDDAKBgNVBAoTA0FVQTEOMAwGA1UEBxMFSW5kaWExDjAMBgNVBAgTBVN0YXRlMQswCQYDVQQGEwJJTjCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAN55oth+k/6/DwxXCclScFlsBRqpm95mjXGtgXYXDTQYfDvner2QTyuruauaC2HoqYDbp/+8l9PJ1G5GWYZgylsecdGWtVo/ebe9fmaHg25cWHmqlPYxYUX16lcyZlCzClnO3SkEAnGteMI3TRR5q/vfJkOgI2vjj6nt5BiB/nKa4z3Wha6X2Nlw4oNr+cYHS5nUDBDC7ypTUolc1DHdz30Fe8TuUUNAPQvU1h5nEWrSxuGxbGahewkTW6gnjSHOJhSTEnOsvHgFzh3quL2umoS6sLo8C5pp5coEXCjpxdn4bRNWMQ4YOr8K+ayMoLl/DzNFN2DgDkHiAn6ulVYxZ+MCAwEAATANBgkqhkiG9w0BAQUFAAOCAQEAwg+h+jSe0o3dhPhxWpDuAWivTofFvuvnBOyI0IGc0xnGrStxdAMhs0EVwTTcc2WhrhG02fR9uBCzXSc5lGyqgLYK8mQDQ6SJmcAFdGbKwxnUk7pCD73OxxZAqz8PZ3xDQ8cQLn02X1ZxjBS4CfUzzDR2CzmD4sVjXweKylbogxWV8Xd4z8T+F6R5yGBptJQ3IrCeiukCkfBE/ucCmrhxJciDrecZMF/18LY9EgzHhyTW5L/6YIzCUrlscYFxd8gR1mDjJUumcDG46GTsReK1nUBZSuk0vMenlDxSWczKXTGWqVLxiw3g4FoF6wVy6bJJNVxALaSQRRG7e10txJ+D+Q==\" ><additional_info><Param name=\"srno\" value=\"H52160818824\" /><Param name=\"pass\" value=\"N\" /></additional_info></DeviceInfo>\n" +
            "<Skey ci=\"20191230\">X26Mtc5P4GNdgsmByutCV3AAygUznM11haohlYCEly9cY6nLmD4CoHiexIJV6+im8tsKs74w3Iwp4f0BV2HAgDbjAI3BCe58ZgEISNE+ZnzKq01man+Kbd5U344W99VtZbF07kmE5QJWuruMiS71yMVmvY7Qjz9LgTfafU2SHwn/QhHWl4fJ1pARSsHMWr9a7RklANtJR1hfs6eGXPVLHJAFB9D7SXDYFU4W2M1h3gVZnLOiLJYJ2nmGC6Aa8tP06tNy0hbN5qsr13deYfpbEK1RLUjJCRP4AR9BaRg/6AdazJ9TMlDKpTKWfmb3pB12mdJ9WExWNoDPvFM/Bitgog==</Skey>\n" +
            "<Hmac>zwKQFIET2Q74TkMo68f9T7ArZnRkP109r6Sb6soR8GZ6Re5zb0HbW7qRlFs0kVJa</Hmac>\n" +
            "<Data type=\"X\">MjAxOC0wMi0wNlQxMjoyNToyMP9OF3Ths8H3cYnprup8Efhwi4zSuK3WvtjRYiSu+i5ltL17h+wlxcZAK8KSHU6O0nWWu5SaLAxQQQ3U4a4sydC53Pp0x0bzJrv0RhAXQFJKUlDkCwGPtVwS6hG+YlbL1kV7SDRNIjEzvaCoqFzzh4nHr44bGuH7/YaVGFO5HtPG6g+FOH4cBphUwPbdo7zXOiwR96AAG+0sqHjqzemkio2Ett1TWdkuxKCI4VMw02mAVG8Go99MnexJ9F7eEOL7wyRcQQnGO3xnmk02ZshHM0fE5htf3Lp4oguSU7FzfHdZTnjSW0FAMGjgClaK/RJtD7jGHYVWp4I+wgpNKWywxpJdvTkDz8YMqkOvune/YEsgqLpTSm1uTjkjxdZuEtLb0vAuZYcQM3MdqY7RWlYydMyIkunK20I8joz2LW3DPKq3VWIwVnFqRg2kPLCM9I9AUrwCfV8Qc41X8B2IUFTDKK78yXuE5n4RMvZG2FZCGHHJlMTOpwNN1ctb3J2trMQPXsAGd3umOukU1xtAN5/bgo4cJadhrIXD8GWKXaz5JEXvcEp7gRFlvaCcGGrcUc9q8swkgaJCsvSU0JUlGb+N/KvS4z6rpYNmZ+4cgLvkj4jlUENR88HuFdXN1oUbY28NTDpFLYJ5u42F/koG2dhGwDAjh3Mz6D4vaODyAOsW/EEZ+TC8yuf8BiVIIkIzaEpAndECwNFcRECfAIdkvhEVOZDck2T2jascj7Hc4s9kgeZiw0oTzFF6kEmGbGV9VcxE+5+8hzJARLY5VEEAWIwwDfmgAohSr/AIT6i/v06bMI1hJaeGeKO/Dj3W9USpgoo3GThb9fbpjC7InYdVqPrBNkEqp+DGIzwIQWVXRo7KMaW2n1OfCDVPS73UNf6mHGs7sbqagi0ki5GrjIXF0L9gP4u6VeI9ELmieQAqhY04hCAc5fV+VnzJHL+VP/Bp3BgeyNokmrwxhCEL/rSGQNgP08FLncF2dEVvLbLbQ5i1ju73NFXElvoAISpA5g4S1dc1Yx/6h+feIaZrnQ5yLp0pn4qxT7r3rP6ACQDAaycf1BT4q0wPRhVsDORJpQcUSozjFSLiYmUk1G8r8/+kW5LXDx/gfkQeyk7PC4P5IsDC/sls3tl41qKccDKUqGUT507Hk5ugbpqnkoDStE3pXfAB3SWDgcNDE3i1dYMUyVUr</Data>\n" +
            "</PidData>";



}

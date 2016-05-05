/**
 * Created by santanuchakraborty on 5/4/16.
 */

$(document).ready(function () {
    $.ajax({
        url: "http://localhost:8080/v1/api/stockdata"
    }).then(function (data) {

        console.log(data);
        var json_obj = data;//parse JSO
        console.log(" json_obj -> " + json_obj);


        //var output = "<p>";
        //for (var i in json_obj) {
        //    output += "<li>" + json_obj[i].compName + " :  " + json_obj[i].prediction + " by " + json_obj[i].predictAmount
        //        +" Model Accuracy "+json_obj[i].accuracy + "</li>";
        //
        //}
        //output += "</p>";
        //$('span').html(output);


        var cName, cPredict, cAmount, cAccuracy;
        for (var i in json_obj) {
            //cName = "<p>"+json_obj[i].compName+ " :  " + json_obj[i].prediction + " by " + json_obj[i].predictAmount
            //    +" Model Accuracy "+json_obj[i].accuracy +"</p>";
            cName = json_obj[i].compName;
            cPredict = json_obj[i].prediction;
            cAmount = json_obj[i].predictAmount;
            cAccuracy = json_obj[i].accuracy;

            if(i == 0) {
                $('.compName').append(cName);
                $('.prediction').append(cPredict);
                $('.predictamount').append(cAmount);
                $('.modelaccu').append(cAccuracy);
            }

            if(i == 1) {
                $('.compName1').append(cName);
                $('.prediction1').append(cPredict);
                $('.predictamount1').append(cAmount);
                $('.modelaccu1').append(cAccuracy);
            }

            if(i == 2) {
                $('.compName2').append(cName);
                $('.prediction2').append(cPredict);
                $('.predictamount2').append(cAmount);
                $('.modelaccu2').append(cAccuracy);
            }

            if(i == 3) {
                $('.compName3').append(cName);
                $('.prediction3').append(cPredict);
                $('.predictamount3').append(cAmount);
                $('.modelaccu3').append(cAccuracy);
            }

            if(i == 4) {
                $('.compName4').append(cName);
                $('.prediction4').append(cPredict);
                $('.predictamount4').append(cAmount);
                $('.modelaccu4').append(cAccuracy);
            }

            if(i == 5) {
                $('.compName5').append(cName);
                $('.prediction5').append(cPredict);
                $('.predictamount5').append(cAmount);
                $('.modelaccu5').append(cAccuracy);
            }

            if(i == 6) {
                $('.compName6').append(cName);
                $('.prediction6').append(cPredict);
                $('.predictamount6').append(cAmount);
                $('.modelaccu6').append(cAccuracy);
            }
            if(i == 7) {
                $('.compName7').append(cName);
                $('.prediction7').append(cPredict);
                $('.predictamount7').append(cAmount);
                $('.modelaccu7').append(cAccuracy);
            }


           // $('.compName').val("");
           // $('.compName').val(cName) ;
            //return false;
        }

    });
});




$(document).ready(function () {
    $.ajax({
        url: "http://localhost:8080/v1/api/biggestgainer"
    }).then(function (data1) {

        console.log(data1);

        $('.biggestGainerCompany').append(data1.compNameBGS);
        $('.biggestGainerAmount').append(data1.percentGainBGS);

    });
});


$(document).ready(function () {
    $.ajax({
        url: "http://localhost:8080/v1/api/biggestloser"
    }).then(function (data2) {

        console.log(data2);

        $('.biggestLoserCompany').append(data2.compNameBGS);
        $('.biggestLoserAmount').append(data2.percentLoserStockBL);

    });
});


$(document).ready(function () {
    $.ajax({
        url: "http://localhost:8080/v1/api/avgmodelaccuracy"
    }).then(function (data3) {

        console.log(data3);

        $('.avgModelAccuracy').append(data3);

    });
});



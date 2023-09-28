#Important note: In this assignment no loops, lists, or dictionaries are allowed! Use simple variables, assignments, math expressions, functions, and if/else statements only!
#1. Implement function, computeTripData(distanceK, vehSpeedMPS, vehKPL, gasCostPerLiter, breakfastCostPerDay, lunchCostPerDay, dinnerCostPerDay, hotelCostPerNight) that takes as input:
#distanceK: the trip's distance in kilometers (type: float)
#vehSpeedMPS: the vehicle's speed during the trip, in meters per second(type: float)
# =============================================================================
# vehKPL: the vehicle fuel efficiency in kilometers per liter (type: float)
# gasCostPerLiter: the price of one liter of gas in dollars (type: float)
# breakfastCostPerDay: cost of a day's breakfast in dollars (type: float)
# lunchCostPerDay: cost of a day's lunch in dollars (type: float)
# dinnerCostPerDay: cost of a day's dinner in dollars (type: float)
# hotelCostPerNight: cost of one night in a hotel in dollars (type: float)
# and returns seven values (in this order):
# the length of the trip in hours (type: float)
# the gas cost of the trip in dollars (type: float)
# the total cost of the trip in dollars (type: float)
# the number of breakfasts required (type: int)
# the number of lunches required (type: int)
# the number of dinners required (type: int)
# the number of hotel nights required (type: int)
# Important rules for cost computation:
# You can only drive 8 hours per day. Thus, if a trip requires 15.25 hours of driving, a one night hotel stay will be needed, costing an additional amount (beyond gas and food costs) equal to hotelCostPerNight. 17 hours of driving would require 2 hotel nights
# For every 40 hours of driving, a rest day is needed, adding one breakfast, lunch, and dinner, as well as two hotel nights to the total cost. Thus, a 39 hour trip requires 4 hotel nights, a 41 hour trip requires 6, a 79.9 hour trip requires 10, and an 80.5 hour trip requires 12.
# No hotel or meals are needed after a trip ends. If a trip requires 8.0 hours, it does not require a hotel stay nor dinner. Similarly, a 40-hour trip requires 4 hotel nights, not 5, and does not require a rest day and the associated costs.
# No breakfast is needed on the first day of a trip.
# Lunch is needed only on days with more than 4.0 hours of travel.
# 2. Implement function, printTripSummary(vehName, distanceM, vehSpeedMPH, vehMPG, gasCostPerGallon, breakfastCostPerDay, lunchCostPerDay, dinnerCostPerDay, hotelCostPerNight) that takes as input:
# vehName: a string representing the vehicle's name (e.g. "Fiat 500")
# distanceM: the trip's distance in miles (type: float)
# vehSpeedMPH: the vehicle's speed in miles per hour (type: float)
# vehMPG: the vehicle's fuel efficiency in miles per gallon (type: float)
# gasCostPerGallon: the price of one gallon of gas in dollars (type: float)
# breakfastCostPerDay: cost of a day's breakfast in dollars (type: float)
# lunchCostPerDay: cost of a day's lunch in dollars (type: float)
# dinnerCostPerDay: cost of a day's dinner in dollars (type: float)
# hotelCostPerNight: cost of one night in a hotel in dollars (type: float)
# Requirements:
# printTripSummary must first convert from the US/non-metric input units (miles and gallons) to metric units (kilometers, meters, liters) suitable for passing to Q1's computeTripData function. 
# printTripSummary must then make a call to Q1's computeTripData function to get the trip data.
# Finally, printTripSummary should construct a string summarizing the trip information, and both print and return that string. The format for the string is shown in the example below. Use the same punctuation and spacing as in the example. Dollar amounts must display two digits after the decimal place (even when it is .00).
# =============================================================================
def computeTripData(distanceK, vehSpeedMPS, vehKPL, gasCostPerLiter, breakfastCostPerDay, lunchCostPerDay, dinnerCostPerDay, hotelCostPerNight):
    speedConversion = vehSpeedMPS * 3.6
    tripTimeHr = distanceK / speedConversion
    gasCost = (distanceK/vehKPL)*gasCostPerLiter
    tripTimeDay = tripTimeHr // 8
    restDays = tripTimeHr // 40
    breakfastAmount, lunchAmount, dinnerAmount = 0,0,0
    hotelAmount = tripTimeDay + restDays
    lunchNeeded = (tripTimeHr/8) - tripTimeDay
    if tripTimeDay > 0:
        if tripTimeHr % 8 == 0 :
            hotelAmount = hotelAmount - 1
            lunchAmount = hotelAmount + 1
        if tripTimeHr % 40 == 0 :
            restDays = restDays - 1
            lunchAmount = restDays + lunchAmount
    if lunchNeeded > .4 :
        lunchAmount = hotelAmount + 1
    else:
        lunchAmount = hotelAmount
    
    dinnerAmount = breakfastAmount = hotelAmount
    hotelCost = hotelAmount * hotelCostPerNight
    breakfastCost = breakfastAmount * breakfastCostPerDay
    lunchCost = lunchAmount * lunchCostPerDay
    dinnerCost = dinnerAmount * dinnerCostPerDay
    tripCost = dinnerCost+lunchCost+breakfastCost+hotelCost+gasCost
    return tripTimeHr,gasCost,tripCost,breakfastAmount,lunchAmount,dinnerAmount,hotelAmount
def printTripSummary(vehName, distanceM, vehSpeedMPH, vehMPG, gasCostPerGallon, breakfastCostPerDay, lunchCostPerDay, dinnerCostPerDay, hotelCostPerNight):
    distanceConverted = distanceM *1.60934
    gasCostConverted = gasCostPerGallon * 3.785411784
    vehSpeedConverted = vehSpeedMPH * 0.44704
    vehLiterConverted = vehMPG * .425144
    tripTime,gasPrice,tripPrice,breakfastCount,lunchCount,dinnerCount,hotelCount = computeTripData(distanceConverted,vehSpeedConverted,vehLiterConverted,gasCostConverted,breakfastCostPerDay,lunchCostPerDay,dinnerCostPerDay,hotelCostPerNight)
    print(vehName,"trip of",distanceM,"miles. Hotel nights:",str((int(hotelCount)))+",","Total cost: $"+(format(tripPrice,".2f")))#did weird string thing on the hotelcount becuase the comma wouldn't go right next to it in the print statment there was a weird space i dint like
    return(tripTime,gasPrice,tripPrice,breakfastCount,lunchCount,dinnerCount,hotelCount)


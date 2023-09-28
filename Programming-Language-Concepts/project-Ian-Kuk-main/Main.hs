module Main where

import System.Environment
import Data.List (find, foldl', partition, sort, permutations)

-- basic structures used 
type Network = [(Int, Int)]
type Wire = (Int,Int)

formatPair :: Wire -> String
formatPair (x, y) = show x ++ " -- " ++ show y

--PART 1

-- Read in the network 
readIn :: FilePath -> IO Network
readIn filename = do
    contents <- readFile filename
    let parsed = reads contents :: [(Network, String)]
    case parsed of
      [(network, "")] -> return network
      _ -> error "Invalid Network"

--write the network in the given format
networkWriter :: FilePath -> Network -> IO()
networkWriter filename network =
    writeFile "network.txt" (unlines (map formatPair network))

--swap the wires if the first is greater than the second
swapWires :: [(Int, Int)] -> [(Int, Int)]
swapWires = map (\(x, y) -> if y < x then (y, x) else (x, y))

-- sort the network
sortNetwork :: Network -> Network
sortNetwork = swapWires

-- PART 2 

-- sorts the values into indicies specified by the wire 
sortList ::  [Int] -> Wire  ->[Int]
sortList  z (x,y) = do
    let atLower = z !! (min x y -1)
    let atHigher = z !! (max x y -1)
    if atHigher < atLower
        then replaceOrder (replaceOrder z (x-1) atHigher) (y-1) atLower
    else
        replaceOrder (replaceOrder z (x-1) atLower) (y-1) atHigher

-- swaps the order of the inputs 
replaceOrder :: [Int] -> Int -> Int -> [Int]
replaceOrder a b c = do
    let (x,_:ys) = splitAt b a
    x ++ [c] ++ ys

--PART 3

-- break the list into one long long 
breakList :: [[(Int, Int)]] -> [(Int, Int)]
breakList = concat

-- takes the broken up list and sorts it
sortTuples :: [(Int, Int)] -> [(Int, Int)]
sortTuples = sort

-- prints the sorted tuples in the specified format
printValues :: [(Int, Int)] -> IO()
printValues = putStrLn . unlines . map (\(x, y) -> formatPair (x, y))

-- takes file and writes the wires in x -- y , x1 -- y1 format 
writeParallel :: FilePath -> [(Int, Int)] -> IO ()
writeParallel filename network = do
    let sortedNetwork = sortNetwork network
    let organised = organiseParallels sortedNetwork
    mapM_ putStrLn organised
    writeFile filename (unlines organised)

-- check if two wires have any equal values
haveEqualValues :: Wire -> Wire -> Bool
haveEqualValues (x1, y1) (x2, y2) = x1 == x2 || y1 == y2 || x1 == y2 || y1 == x2

-- print head and tail of the list together in the specified format and remove them from the list
organiseParallels :: [(Int, Int)] -> [String]
organiseParallels [] = []
organiseParallels [(x, y)] = [formatPair (x, y)]
organiseParallels ((x1, y1):(x2, y2):xs)
    -- checks if any of the values in x -- y , x1 -- y1 are equal to each other 
  | haveEqualValues (x1, y1) (x2, y2) = organiseParallels ((x1, y1):xs)
  | otherwise = (formatPair (x1, y1) ++ " , " ++ formatPair (x2, y2)) : organiseParallels xs

-- PART 4

-- checks to see if the given network is a sorting network 
checkSortNet :: Network -> Bool
checkSortNet network =
  -- zero one principal
  all (sortCheck . (`sortNetwork2` [0, 1])) bitList
    where
        bitList = permutations [(0, 1), (1, 0)]
        sortCheck a = and $ zipWith (<=) a (tail a)

-- Redo of swap wires for the comparison 
swapWiresComp :: Wire -> [Int] -> [Int]
swapWiresComp (x, y) z = [if k == x then z !! y else if k == y then z !! x else z !! k | k <- [0..length z - 1]]

-- new network sorter to apply the new swapWiresComp
sortNetwork2 :: Network -> [Int] -> [Int]
sortNetwork2 network b = foldl' (flip swapWiresComp) b network

--main
main :: IO()
main = do
  args <- getArgs
  case args of
    []-> fail "Run with command line"
    "Read":filename:_ -> do
        network <- readIn filename
        let sortedNetwork = sortNetwork network
        networkWriter "network.txt" sortedNetwork
    "Run":filename:sequence:_ -> do
        network <- readIn filename
        let sortedNetwork = sortNetwork network
        print (foldl sortList (read sequence :: [Int]) sortedNetwork)
    "Parallel":filename:_ -> do
        network <- readIn filename 
        let sortedNetwork = sortNetwork network
        writeParallel "parallel.txt" sortedNetwork
    --This implemenation is different but it was what was used in Lecture 
    -- for the example problem #1 
    "Sorting":filename:_ -> do
        inputSort <- readFile filename
        let network = read inputSort :: Network in
            print (checkSortNet network)
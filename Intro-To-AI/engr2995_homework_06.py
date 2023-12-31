# -*- coding: utf-8 -*-
"""ENGR2995 - Homework 06.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/15WWtqnlOdcd2pxmhuZdeyKBjdb4yee4I
"""

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

"""### Guidelines for data plotting

When asked to produce a plot, you must:

1. Follow directions on whether to use a line plot or scatter plot
2. Provide descriptive labels for all axes and give units when indicated
3. Provide a descriptive title for the plot
4. Provide a descriptive legend for the plot when plotting more than one dataset
5. Use color or marker to differentiate between datasets when plotting more than one dataset

### Guidelines for report numerical and text-based answers

1. When asked to report a numerical value (e.g. a validation score), you must include a print statement that describes the value being reported either together or directly before the printing of the value itself.

2. When asked to answer a question with text, create a new text cell and answer the question in complete sentances.

# 1. Estimating the Accuracy of Linear Regression Model

The bootstrap approach can be used to assess the variability of the coefficient estimates and predictions from a machine learning model. Here we use the bootstrap approach to assess the variability of the estimates for $\hat{\beta}_0$ and $\hat{\beta}_1$, the intercept and slope terms for the linear regression model. We will build a linear regression model that predicts `mpg` (miles per gallon) as a function of `horsepower` using the `Auto` dataset.

### Step 1: Load dataset

**Load the dataset into a dataframe. Drop the `name` column. Remove all examples with missing values and ensure that the remaining columns all have numeric (`float`, `int`) datatypes.** You should not have any columns with `object` datatype.
"""

!wget https://www.statlearning.com/s/Auto.csv



"""### Step 2: Perform univariate linear regression

Fit the `mpg` (y, dependent variable) as a function of `horsepower` (x, independent variable) using a univariate linear regression model

$$
y = \hat{\beta}_0 + \hat{\beta}_1 x
$$

**Print out the value of the slope ($\hat{\beta}_1$) and intercept ($\hat{\beta}_0$).**

**Construct a plot showing the original dataset and model predictions.** The plot should contain (1) a scatter plot showing the `horsepower` (x-axis) and `mpg` (y-axis) and (2) a line plot showing the linear regression model. Be sure to follow all plotting guidelines.
"""



"""### Step 3: Estimate the standard error of the linear coefficients using the Bootstrap method

Produce $R=1000$ bootstrap estimates of the slope ($\hat{\beta}_1$) and intercept ($\hat{\beta}_0$). Your solution should:

1. Construct 1000 unique bootstrap datasets (sample with replacement) the same size as the original dataset (n=392).
2. Fit a univariate regression model for each bootstrap dataset. Construct a collection object to store the slope ($\hat{\beta}_1$) and intercept ($\hat{\beta}_0$) of each model for later use.
3. Estimate the standard error of the mean for the slope ($\hat{\beta}_1$) and intercept ($\hat{\beta}_0$) using the 1000 estimates. HINT: `scipy.stats.sem`

**Print out the bootstrap esimate of the standard error of the slope and intercept.**
"""



"""### Step 4: Compute the standard error of the linear coefficients using formulas

The standard error of the mean value of the linear coefficients can be estimated using standard formulas

$$
\text{SE}(\hat{\beta}_0)^2 = \sigma^2 \bigg[ \frac{1}{n} + \frac{\bar{x}^2}{\sum_{i=1}^n(x_i-\bar{x})^2} \bigg]
$$

$$
\text{SE}(\hat{\beta}_1)^2 = \frac{\sigma^2}{\sum_{i=1}^n (x_i-\bar{x})^2}
$$

Here, $\sigma$ is residual standard error that can be computed using the estimator

$$
\sigma^2 = \frac{\sum_{i=1}^n (y_i - \hat{y}_i)^2}{n-2}
$$

Where $y_i$ is the true `mpg` of the $i$-th example and $\hat{y}_i$ is the linear regression estimate. The $n-2$ in the denominator corresponds to the number of degrees of freedom in univariate linear regression. $n$ is the number of data points.

**Print out the formula esimate of the standard error of the slope and intercept.**
"""



"""### NOTE

The standard error can be used to estimate the 95\% confidence interval of the linear regression coefficients. There is a 95\% chance that the interval $[\hat{\beta}_1 - 2 \text{SE}(\hat{\beta}), \hat{\beta}_1 + 2 \text{SE}(\hat{\beta})]$ will contain the true value of $\hat{\beta}_1$. And similar for $\hat{\beta}_0$.

You should observe some difference between the two computed confidence intervals. The standard formulas used in Step 4 rely on the correct estimation of the variance $\sigma^2$ from the residual error. These residual are inflated, and therefore so is $\sigma^2$, due to the observed nonlinearity of the data. In this case, the bootstrap estimate is generally more correct.

# 2. Revisiting the Diabetic Retinopathy dataset with Discriminant Analysis

This dataset contains features extracted from the Messidor image set to predict whether an image contains signs of diabetic retinopathy, an eye condition that can cause vision loss and blindness in people who have diabetes. All features (columns `0` - `18`) represent either a detected lesion, a descriptive feature of a anatomical part or an image-level descriptor. The final column (`Class`) is the class label where 1 = contains signs of diabetic retinopathy and 0 = no sign of diabetic retinopathy.

### Step 0: Load the dataset
"""

!wget https://archive.ics.uci.edu/ml/machine-learning-databases/00329/messidor_features.arff

from scipy.io.arff import loadarff
raw_data = loadarff('messidor_features.arff')
df = pd.DataFrame(raw_data[0]).astype({'Class': int})
df

"""### Step 1: Prepare the data for modeling

1. **Prepare the target data `y` containing the data found in the `Class` column.**
2. **Prepare the design matrix `X` containing the data found in the remaining columns.** Be sure to drop the `Class` column before generating `X`.
3. **Split the dataset into training and testing sets.**
"""

y = df['Class'].values
X = df.drop('Class', axis=1).values
X_train, X_test, y_train, y_test = train_test_split(X, y, random_state=1234)

"""### Step 2: Model Selection

1. **Construct a modeling `sklearn.pipeline.Pipeline` for fitting the data.** The pipeline should include:
  - `sklearn.preprocessing.StandardScaler` standardization transformation.
  - `sklearn.naive_bayes.GaussianNB` Gaussian Naive Bayes model.
2. **Perform 5-fold cross-validation to evaluate the cross-validation score.**
3. **Repeat the analysis, replacing the `GaussianNB` model** with
  - `sklearn.discriminant_analysis.LinearDiscriminantAnalysis` LDA model.
  - `sklearn.discriminant_analysis.QuadraticDiscriminantAnalysis` QDA model.
4. **Print out the best cross-validation score and corresponding model.**
"""



"""### Step 3: Model testing and confusion matrix

Using the best model from step 2, retrain the model on the training set. **Generate a confusion matrix based on ground truth data and predictions of the model on the testing set. Identify the number of true positive (TP), true negative (TN), false positive (FP), and false negative (FN) model predictions.**
"""



"""### Step 4: Model precision

**Compute the `precision_score` for the positive and negative classes based on the ground truth data and predictions of the model on the testing set. Comment on the how well we are able to trust positive classifications and negative classifications given by our model.**
"""



"""# 3. Revisiting the Steel Plate Fault Analysis dataset with Random Forest

The goal of this learning task is to correctly classify the type of surface defects in stainless steel plates, with seven types of possible defects. The features are a set of 27 indicators that approximately describe the geometric shape of the defect and its outline.

The feature attribute information can be found in Homework 03.

### Step 0: Load the dataset
"""



"""### Step 1: Prepare the data for modeling

For this homework problem, **we will focus on the `Bumps` prediction task.** **Split the dataset into features (`X`) and task/labels (`y`). Split the features/labels into training and testing sets.** Be sure to include only the `Bumps` task in the labels array, and be sure to exclude all task/label data from the features.
"""



"""### Step 2: Hyperparameter optimization

1. **Construct a modeling `sklearn.pipeline.Pipeline` for fitting the data.** The pipeline should include:
  - `sklearn.preprocessing.StandardScaler` standardization transformation.
  - `sklearn.ensemble.RandomForestClassifier` random forest classification model.
2. **Perform a grid search `GridSearchCV`** over
  - `n_estimators`: the number of ensembled decision trees.
  - `max_depth`: the maximum depth of a single decision tree.
3. **Print out the best cross-validation score and corresponding parameters.**

You may select the range of possible hyperparameter values, but you must compare at least 25 total models.
"""



"""### Step 3: Model testing and confusion matrix

Using the best model from step 2, retrain the model on the training set. **Generate a confusion matrix based on ground truth data and predictions of the model on the testing set. Identify the number of true positive (TP), true negative (TN), false positive (FP), and false negative (FN) model predictions.**
"""



"""### Step 4: Model precision

**Compute the `precision_score` for the positive and negative classes based on the ground truth data and predictions of the model on the testing set. Comment on the how well we are able to trust positive classifications and negative classifications given by our model.**
"""


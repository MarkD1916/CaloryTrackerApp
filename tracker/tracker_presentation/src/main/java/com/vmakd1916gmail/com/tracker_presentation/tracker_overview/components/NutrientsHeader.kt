package com.vmakd1916gmail.com.tracker_presentation.tracker_overview.components

import android.text.Layout
import android.util.Log
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vmakd1916gmail.com.core_ui.LocalSpacing
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import com.plcoding.core_ui.CarbColor
import com.plcoding.core_ui.FatColor
import com.plcoding.core_ui.ProteinColor
import com.vmakd1916gmail.com.tracker_presentation.R
import com.vmakd1916gmail.com.tracker_presentation.components.UnitDisplay
import com.vmakd1916gmail.com.tracker_presentation.tracker_overview.TrackerOverviewState


@Composable
fun NutrientsHeader(
    state: TrackerOverviewState,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    val animatedCalorieCount = animateIntAsState(
        targetValue = state.totalCalories
    )
    val animatedCalorieGoalCount = animateIntAsState(
        targetValue = state.caloriesGoal
    )
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    bottomEnd = 50.dp,
                    bottomStart = 50.dp
                )
            )
            .background(MaterialTheme.colors.primary)
            .padding(
                horizontal = spacing.spaceLarge,
                vertical = spacing.spaceExtraLarge
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            UnitDisplay(
                amount = animatedCalorieCount.value,
                unit = stringResource(id = R.string.kcal),
                amountColor = MaterialTheme.colors.onPrimary,
                amountTextSize = 40.sp,
                unitColor = MaterialTheme.colors.onPrimary,
                modifier = Modifier.align(Alignment.Bottom)
            )

            Column {
                Text(
                    text = stringResource(R.string.your_goal),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onPrimary
                )
                UnitDisplay(
                    amount = animatedCalorieGoalCount.value,
                    unit = stringResource(id = R.string.kcal),
                    amountColor = MaterialTheme.colors.onPrimary,
                    amountTextSize = 40.sp,
                    unitColor = MaterialTheme.colors.onPrimary,
                )
            }
        }
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        NutrientsBar(
            carbs = state.totalCarbs,
            protein = state.totalProtein,
            fat = state.totalFat,
            calories = state.totalCalories,
            calorieGoal = state.caloriesGoal,
            modifier = modifier
                .fillMaxWidth()
                .height(30.dp)
        )
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Log.d("NutrientsHeader", "NutrientsHeader: ${state.totalCarbs}, ${state.carbsGoal}")
            NutrientBarInfo(
                value = state.totalCarbs,
                goal = state.carbsGoal,
                name = stringResource(
                    id = R.string.carbs
                ),
                color = CarbColor,
                modifier = Modifier.size(90.dp)
            )
            NutrientBarInfo(
                value = state.totalProtein,
                goal = state.proteinGoal,
                name = stringResource(
                    id = R.string.protein
                ),
                color = ProteinColor,
                modifier = Modifier.size(90.dp)
            )
            NutrientBarInfo(
                value = state.totalFat,
                goal = state.fatGoal,
                name = stringResource(
                    id = R.string.fat
                ),
                color = FatColor,
                modifier = Modifier.size(90.dp)
            )
        }
    }
}
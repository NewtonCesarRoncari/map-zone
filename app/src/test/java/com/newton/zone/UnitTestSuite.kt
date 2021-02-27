package com.newton.zone

import com.newton.zone.database.converter.ConvertersTest
import com.newton.zone.extension.BigDecimalExtensionKtTest
import com.newton.zone.extension.StringExtensionKtTest
import com.newton.zone.model.QueryCreatorFilterTest
import com.newton.zone.view.recyclerview.adapter.BusinessAdapterTest
import com.newton.zone.view.recyclerview.adapter.VisitAdapterTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    ConvertersTest::class,
    BigDecimalExtensionKtTest::class,
    StringExtensionKtTest::class,
    QueryCreatorFilterTest::class,
    BusinessAdapterTest::class,
    VisitAdapterTest::class,
)
class UnitTestSuite
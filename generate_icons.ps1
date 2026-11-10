Add-Type -AssemblyName System.Drawing

function Create-Icon([int]$size, [string]$outputPath) {
    $bmp = New-Object System.Drawing.Bitmap $size, $size
    $g = [System.Drawing.Graphics]::FromImage($bmp)
    $g.SmoothingMode = [System.Drawing.Drawing2D.SmoothingMode]::AntiAlias
    $g.InterpolationMode = [System.Drawing.Drawing2D.InterpolationMode]::HighQualityBicubic

    # Dark background
    $bgBrush = New-Object System.Drawing.SolidBrush ([System.Drawing.Color]::FromArgb(18, 20, 32))
    $g.FillRectangle($bgBrush, 0, 0, $size, $size)

    # Cyan Outer Circle
    $center = [float]($size / 2.0)
    $radius = [float]($size * 0.42)
    $cyanPen = New-Object System.Drawing.Pen ([System.Drawing.Color]::FromArgb(0, 210, 255), [float]($size * 0.04))
    $g.DrawEllipse($cyanPen, [float]($center - $radius), [float]($center - $radius), [float]($radius * 2), [float]($radius * 2))

    # Inner Emerald Cogwheel Circle
    $innerRadius = [float]($size * 0.32)
    $emeraldBrush = New-Object System.Drawing.SolidBrush ([System.Drawing.Color]::FromArgb(0, 230, 118))
    $g.FillEllipse($emeraldBrush, [float]($center - $innerRadius), [float]($center - $innerRadius), [float]($innerRadius * 2), [float]($innerRadius * 2))

    # Inner Dark Core
    $coreRadius = [float]($size * 0.22)
    $darkBrush = New-Object System.Drawing.SolidBrush ([System.Drawing.Color]::FromArgb(20, 24, 38))
    $g.FillEllipse($darkBrush, [float]($center - $coreRadius), [float]($center - $coreRadius), [float]($coreRadius * 2), [float]($coreRadius * 2))

    # Gold Quill / Stylized Symbol
    $goldBrush = New-Object System.Drawing.SolidBrush ([System.Drawing.Color]::FromArgb(245, 166, 35))
    $p1 = [System.Drawing.PointF]::new($center, [float]($center - $size * 0.16))
    $p2 = [System.Drawing.PointF]::new([float]($center + $size * 0.10), [float]($center + $size * 0.04))
    $p3 = [System.Drawing.PointF]::new([float]($center + $size * 0.04), [float]($center + $size * 0.18))
    $p4 = [System.Drawing.PointF]::new($center, [float]($center + $size * 0.22))
    $p5 = [System.Drawing.PointF]::new([float]($center - $size * 0.04), [float]($center + $size * 0.18))
    $p6 = [System.Drawing.PointF]::new([float]($center - $size * 0.10), [float]($center + $size * 0.04))

    [System.Drawing.PointF[]]$pts = @($p1, $p2, $p3, $p4, $p5, $p6)
    $g.FillPolygon($goldBrush, $pts)

    # Center Cyan dot
    $dotBrush = New-Object System.Drawing.SolidBrush ([System.Drawing.Color]::FromArgb(0, 210, 255))
    $dotR = [float]($size * 0.035)
    $g.FillEllipse($dotBrush, [float]($center - $dotR), [float]($center - $dotR), [float]($dotR * 2), [float]($dotR * 2))

    $g.Dispose()
    $bmp.Save($outputPath, [System.Drawing.Imaging.ImageFormat]::Png)
    $bmp.Dispose()
    Write-Host "Created $outputPath ($size x $size)"
}

Create-Icon 192 "d:\EnglishTeacher\ielts-writing-accelerator\web\icon-192.png"
Create-Icon 512 "d:\EnglishTeacher\ielts-writing-accelerator\web\icon-512.png"
Create-Icon 180 "d:\EnglishTeacher\ielts-writing-accelerator\web\apple-touch-icon.png"
